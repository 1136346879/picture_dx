/*
 * This is the source code of Telegram for Android v. 5.x.x.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013-2018.
 */

package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.EmptyCell;
import org.telegram.ui.Cells.StickerEmojiCell;
import org.telegram.ui.ContentPreviewViewer;

import java.util.ArrayList;
import java.util.HashMap;

import android.recyclerview.widget.GridLayoutManager;
import android.recyclerview.widget.RecyclerTmView;

@SuppressWarnings("unchecked")
public class StickerMasksView extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {

    public interface Listener {
        void onStickerSelected(Object parentObject, TLRPC.Document sticker);
        void onTypeChanged();
    }

    private int currentAccount = UserConfig.selectedAccount;
    private ArrayList<TLRPC.TL_messages_stickerSet>[] stickerSets = new ArrayList[]{new ArrayList<>(), new ArrayList<>()};
    private ArrayList<TLRPC.Document>[] recentStickers = new ArrayList[]{new ArrayList<>(), new ArrayList<>()};
    private int currentType = MediaDataController.TYPE_MASK;

    private Listener listener;
    private StickersGridAdapter stickersGridAdapter;
    private ScrollSlidingTabStrip scrollSlidingTabStrip;
    private RecyclerTmListView stickersGridView;
    private GridLayoutManager stickersLayoutManager;
    private TextView stickersEmptyView;
    private RecyclerTmListView.OnItemClickListener stickersOnItemClickListener;

    private int stickersTabOffset;
    private int recentTabBum = -2;

    private int lastNotifyWidth;

    public StickerMasksView(final Context context) {
        super(context);
        setBackgroundColor(0xff222222);
        setClickable(true);

        MediaDataController.getInstance(currentAccount).checkStickers(MediaDataController.TYPE_IMAGE);
        MediaDataController.getInstance(currentAccount).checkStickers(MediaDataController.TYPE_MASK);
        stickersGridView = new RecyclerTmListView(context) {
            @Override
            public boolean onInterceptTouchEvent(MotionEvent event) {
                boolean result = ContentPreviewViewer.getInstance().onInterceptTouchEvent(event, stickersGridView, StickerMasksView.this.getMeasuredHeight(), null);
                return super.onInterceptTouchEvent(event) || result;
            }
        };

        stickersGridView.setLayoutManager(stickersLayoutManager = new GridLayoutManager(context, 5));
        stickersLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == stickersGridAdapter.totalItems) {
                    return stickersGridAdapter.stickersPerRow;
                }
                return 1;
            }
        });
        stickersGridView.setPadding(0, AndroidUtilities.dp(4), 0, 0);
        stickersGridView.setClipToPadding(false);
        stickersGridView.setAdapter(stickersGridAdapter = new StickersGridAdapter(context));
        stickersGridView.setOnTouchListener((v, event) -> ContentPreviewViewer.getInstance().onTouch(event, stickersGridView, StickerMasksView.this.getMeasuredHeight(), stickersOnItemClickListener, null));
        stickersOnItemClickListener = (view, position) -> {
            if (!(view instanceof StickerEmojiCell)) {
                return;
            }
            ContentPreviewViewer.getInstance().reset();
            StickerEmojiCell cell = (StickerEmojiCell) view;
            if (cell.isDisabled()) {
                return;
            }
            TLRPC.Document document = cell.getSticker();
            Object parent = cell.getParentObject();
            listener.onStickerSelected(parent, document);
            MediaDataController.getInstance(currentAccount).addRecentSticker(MediaDataController.TYPE_MASK, parent, document, (int) (System.currentTimeMillis() / 1000), false);
            MessagesController.getInstance(currentAccount).saveRecentSticker(parent, document, true);
        };
        stickersGridView.setOnItemClickListener(stickersOnItemClickListener);
        stickersGridView.setGlowColor(0xfff5f6f7);
        addView(stickersGridView, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT, Gravity.LEFT | Gravity.TOP, 0, 48, 0, 0));

        stickersEmptyView = new TextView(context);
        stickersEmptyView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        stickersEmptyView.setTextColor(0xff888888);
        addView(stickersEmptyView, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER, 0, 48, 0, 0));
        stickersGridView.setEmptyView(stickersEmptyView);

        scrollSlidingTabStrip = new ScrollSlidingTabStrip(context);
        scrollSlidingTabStrip.setBackgroundColor(0xff000000);
        scrollSlidingTabStrip.setUnderlineHeight(AndroidUtilities.dp(1));
        scrollSlidingTabStrip.setIndicatorColor(0xff62bfe8);
        scrollSlidingTabStrip.setUnderlineColor(0xff1a1a1a);
        scrollSlidingTabStrip.setIndicatorHeight(AndroidUtilities.dp(1) + 1);
        addView(scrollSlidingTabStrip, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, 48, Gravity.LEFT | Gravity.TOP));
        updateStickerTabs();
        scrollSlidingTabStrip.setDelegate(page -> {
            if (page == 0) {
                if (currentType == MediaDataController.TYPE_IMAGE) {
                    currentType = MediaDataController.TYPE_MASK;
                } else {
                    currentType = MediaDataController.TYPE_IMAGE;
                }
                if (listener != null) {
                    listener.onTypeChanged();
                }
                recentStickers[currentType] = MediaDataController.getInstance(currentAccount).getRecentStickers(currentType);
                stickersLayoutManager.scrollToPositionWithOffset(0, 0);
                updateStickerTabs();
                reloadStickersAdapter();
                checkDocuments();
                checkPanels();
                return;
            }
            if (page == recentTabBum + 1) {
                stickersLayoutManager.scrollToPositionWithOffset(0, 0);
                return;
            }
            int index = page - 1 - stickersTabOffset;
            if (index >= stickerSets[currentType].size()) {
                index = stickerSets[currentType].size() - 1;
            }
            stickersLayoutManager.scrollToPositionWithOffset(stickersGridAdapter.getPositionForPack(stickerSets[currentType].get(index)), 0);
            checkScroll();
        });

        stickersGridView.setOnScrollListener(new RecyclerTmView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerTmView recyclerTmView, int dx, int dy) {
                checkScroll();
            }
        });
    }

    private void checkScroll() {
        int firstVisibleItem = stickersLayoutManager.findFirstVisibleItemPosition();
        if (firstVisibleItem == RecyclerTmView.NO_POSITION) {
            return;
        }
        checkStickersScroll(firstVisibleItem);
    }

    private void checkStickersScroll(int firstVisibleItem) {
        if (stickersGridView == null) {
            return;
        }
        scrollSlidingTabStrip.onPageScrolled(stickersGridAdapter.getTabForPosition(firstVisibleItem) + 1, (recentTabBum > 0 ? recentTabBum : stickersTabOffset) + 1);
    }

    public int getCurrentType() {
        return currentType;
    }

    private void updateStickerTabs() {
        if (scrollSlidingTabStrip == null) {
            return;
        }
        recentTabBum = -2;

        stickersTabOffset = 0;
        int lastPosition = scrollSlidingTabStrip.getCurrentPosition();
        scrollSlidingTabStrip.removeTabs();
        if (currentType == MediaDataController.TYPE_IMAGE) {
            Drawable drawable = getContext().getResources().getDrawable(R.drawable.ic_masks_msk1);
            Theme.setDrawableColorByKey(drawable, Theme.key_chat_emojiPanelIcon);
            scrollSlidingTabStrip.addIconTab(drawable);
            stickersEmptyView.setText(LocaleController.getString("NoStickers", R.string.NoStickers));
        } else {
            Drawable drawable = getContext().getResources().getDrawable(R.drawable.ic_masks_sticker1);
            Theme.setDrawableColorByKey(drawable, Theme.key_chat_emojiPanelIcon);
            scrollSlidingTabStrip.addIconTab(drawable);
            stickersEmptyView.setText(LocaleController.getString("NoMasks", R.string.NoMasks));
        }

        if (!recentStickers[currentType].isEmpty()) {
            recentTabBum = stickersTabOffset;
            stickersTabOffset++;
            scrollSlidingTabStrip.addIconTab(Theme.createEmojiIconSelectorDrawable(getContext(), R.drawable.ic_masks_recent1, Theme.getColor(Theme.key_chat_emojiPanelMasksIcon), Theme.getColor(Theme.key_chat_emojiPanelMasksIconSelected)));
        }

        stickerSets[currentType].clear();
        ArrayList<TLRPC.TL_messages_stickerSet> packs = MediaDataController.getInstance(currentAccount).getStickerSets(currentType);
        for (int a = 0; a < packs.size(); a++) {
            TLRPC.TL_messages_stickerSet pack = packs.get(a);
            if (pack.set.archived || pack.documents == null || pack.documents.isEmpty()) {
                continue;
            }
            stickerSets[currentType].add(pack);
        }
        for (int a = 0; a < stickerSets[currentType].size(); a++) {
            TLRPC.TL_messages_stickerSet set = stickerSets[currentType].get(a);
            TLRPC.Document document = set.documents.get(0);
            scrollSlidingTabStrip.addStickerTab(document, document, set);
        }
        scrollSlidingTabStrip.updateTabStyles();
        if (lastPosition != 0) {
            scrollSlidingTabStrip.onPageScrolled(lastPosition, lastPosition);
        }
        checkPanels();
    }

    private void checkPanels() {
        if (scrollSlidingTabStrip == null) {
            return;
        }
        int position = stickersLayoutManager.findFirstVisibleItemPosition();
        if (position != RecyclerTmView.NO_POSITION) {
            scrollSlidingTabStrip.onPageScrolled(stickersGridAdapter.getTabForPosition(position) + 1, (recentTabBum > 0 ? recentTabBum : stickersTabOffset) + 1);
        }
    }

    public void addRecentSticker(TLRPC.Document document) {
        if (document == null) {
            return;
        }
        MediaDataController.getInstance(currentAccount).addRecentSticker(currentType, null, document, (int) (System.currentTimeMillis() / 1000), false);
        boolean wasEmpty = recentStickers[currentType].isEmpty();
        recentStickers[currentType] = MediaDataController.getInstance(currentAccount).getRecentStickers(currentType);
        if (stickersGridAdapter != null) {
            stickersGridAdapter.notifyDataSetChanged();
        }
        if (wasEmpty) {
            updateStickerTabs();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (lastNotifyWidth != right - left) {
            lastNotifyWidth = right - left;
            reloadStickersAdapter();
        }
        super.onLayout(changed, left, top, right, bottom);
    }

    private void reloadStickersAdapter() {
        if (stickersGridAdapter != null) {
            stickersGridAdapter.notifyDataSetChanged();
        }
        if (ContentPreviewViewer.getInstance().isVisible()) {
            ContentPreviewViewer.getInstance().close();
        }
        ContentPreviewViewer.getInstance().reset();
    }

    public void setListener(Listener value) {
        listener = value;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(currentAccount).addObserver(this, NotificationCenter.stickersDidLoad);
        NotificationCenter.getInstance(currentAccount).addObserver(this, NotificationCenter.recentImagesDidLoad);
        AndroidUtilities.runOnUIThread(() -> {
            updateStickerTabs();
            reloadStickersAdapter();
        });
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility != GONE) {
            NotificationCenter.getInstance(currentAccount).addObserver(this, NotificationCenter.stickersDidLoad);
            NotificationCenter.getInstance(currentAccount).addObserver(this, NotificationCenter.recentDocumentsDidLoad);
            updateStickerTabs();
            reloadStickersAdapter();
            checkDocuments();
            MediaDataController.getInstance(currentAccount).loadRecents(MediaDataController.TYPE_IMAGE, false, true, false);
            MediaDataController.getInstance(currentAccount).loadRecents(MediaDataController.TYPE_MASK, false, true, false);
            MediaDataController.getInstance(currentAccount).loadRecents(MediaDataController.TYPE_FAVE, false, true, false);
        }
    }

    public void onDestroy() {
        if (stickersGridAdapter != null) {
            NotificationCenter.getInstance(currentAccount).removeObserver(this, NotificationCenter.stickersDidLoad);
            NotificationCenter.getInstance(currentAccount).removeObserver(this, NotificationCenter.recentDocumentsDidLoad);
        }
    }

    private void checkDocuments() {
        int previousCount = recentStickers[currentType].size();
        recentStickers[currentType] = MediaDataController.getInstance(currentAccount).getRecentStickers(currentType);
        if (stickersGridAdapter != null) {
            stickersGridAdapter.notifyDataSetChanged();
        }
        if (previousCount != recentStickers[currentType].size()) {
            updateStickerTabs();
        }
    }

    @Override
    public void didReceivedNotification(int id, int account, Object... args) {
        if (id == NotificationCenter.stickersDidLoad) {
            if ((Integer) args[0] == currentType) {
                updateStickerTabs();
                reloadStickersAdapter();
                checkPanels();
            }
        } else if (id == NotificationCenter.recentDocumentsDidLoad) {
            boolean isGif = (Boolean) args[0];
            if (!isGif && (Integer) args[1] == currentType) {
                checkDocuments();
            }
        }
    }

    private class StickersGridAdapter extends RecyclerTmListView.SelectionAdapter {

        private Context context;
        private int stickersPerRow;
        private SparseArray<TLRPC.TL_messages_stickerSet> rowStartPack = new SparseArray<>();
        private HashMap<TLRPC.TL_messages_stickerSet, Integer> packStartRow = new HashMap<>();
        private SparseArray<TLRPC.Document> cache = new SparseArray<>();
        private SparseArray<TLRPC.TL_messages_stickerSet> positionsToSets = new SparseArray<>();
        private int totalItems;

        public StickersGridAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getItemCount() {
            return totalItems != 0 ? totalItems + 1 : 0;
        }

        public Object getItem(int i) {
            return cache.get(i);
        }

        public int getPositionForPack(TLRPC.TL_messages_stickerSet stickerSet) {
            return packStartRow.get(stickerSet) * stickersPerRow;
        }

        @Override
        public int getItemViewType(int position) {
            if (cache.get(position) != null) {
                return 0;
            }
            return 1;
        }

        public int getTabForPosition(int position) {
            if (stickersPerRow == 0) {
                int width = getMeasuredWidth();
                if (width == 0) {
                    width = AndroidUtilities.displaySize.x;
                }
                stickersPerRow = width / AndroidUtilities.dp(72);
            }
            int row = position / stickersPerRow;
            TLRPC.TL_messages_stickerSet pack = rowStartPack.get(row);
            if (pack == null) {
                return recentTabBum;
            }
            return stickerSets[currentType].indexOf(pack) + stickersTabOffset;
        }

        @Override
        public boolean isEnabled(RecyclerTmView.ViewHolder holder) {
            return false;
        }

        @Override
        public RecyclerTmView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            switch (viewType) {
                case 0:
                    view = new StickerEmojiCell(context) {
                        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                            super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(82), MeasureSpec.EXACTLY));
                        }
                    };
                    break;
                case 1:
                    view = new EmptyCell(context);
                    break;
            }

            return new RecyclerTmListView.Holder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerTmView.ViewHolder holder, int position) {
            switch (holder.getItemViewType()) {
                case 0:
                    TLRPC.Document sticker = cache.get(position);
                    ((StickerEmojiCell) holder.itemView).setSticker(sticker, positionsToSets.get(position), false);
                    break;
                case 1:
                    if (position == totalItems) {
                        int row = (position - 1) / stickersPerRow;
                        TLRPC.TL_messages_stickerSet pack = rowStartPack.get(row);
                        if (pack == null) {
                            ((EmptyCell) holder.itemView).setHeight(1);
                        } else {
                            int height = stickersGridView.getMeasuredHeight() - (int) Math.ceil(pack.documents.size() / (float) stickersPerRow) * AndroidUtilities.dp(82);
                            ((EmptyCell) holder.itemView).setHeight(height > 0 ? height : 1);
                        }
                    } else {
                        ((EmptyCell) holder.itemView).setHeight(AndroidUtilities.dp(82));
                    }
                    break;
            }
        }

        @Override
        public void notifyDataSetChanged() {
            int width = getMeasuredWidth();
            if (width == 0) {
                width = AndroidUtilities.displaySize.x;
            }
            stickersPerRow = width / AndroidUtilities.dp(72);
            stickersLayoutManager.setSpanCount(stickersPerRow);
            rowStartPack.clear();
            packStartRow.clear();
            cache.clear();
            positionsToSets.clear();
            totalItems = 0;
            ArrayList<TLRPC.TL_messages_stickerSet> packs = stickerSets[currentType];
            for (int a = -1; a < packs.size(); a++) {
                ArrayList<TLRPC.Document> documents;
                TLRPC.TL_messages_stickerSet pack = null;
                int startRow = totalItems / stickersPerRow;
                if (a == -1) {
                    documents = recentStickers[currentType];
                } else {
                    pack = packs.get(a);
                    documents = pack.documents;
                    packStartRow.put(pack, startRow);
                }
                if (documents.isEmpty()) {
                    continue;
                }
                int count = (int) Math.ceil(documents.size() / (float) stickersPerRow);
                for (int b = 0; b < documents.size(); b++) {
                    cache.put(b + totalItems, documents.get(b));
                    positionsToSets.put(b + totalItems, pack);
                }
                totalItems += count * stickersPerRow;
                for (int b = 0; b < count; b++) {
                    rowStartPack.put(startRow + b, pack);
                }
            }
            super.notifyDataSetChanged();
        }
    }
}
