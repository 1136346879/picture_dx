/*
 * This is the source code of Telegram for Android v. 5.x.x
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013-2018.
 */

package org.telegram.ui.Adapters;

import android.content.Context;
import android.view.ViewGroup;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Cells.LocationCell;
import org.telegram.ui.Components.RecyclerTmListView;

import android.recyclerview.widget.RecyclerTmView;

public class LocationActivitySearchAdapter extends BaseLocationAdapter {

    private Context mContext;

    public LocationActivitySearchAdapter(Context context) {
        super();
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    @Override
    public RecyclerTmView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerTmListView.Holder(new LocationCell(mContext));
    }

    @Override
    public void onBindViewHolder(RecyclerTmView.ViewHolder holder, int position) {
        ((LocationCell) holder.itemView).setLocation(places.get(position), iconUrls.get(position), position != places.size() - 1);
    }

    public TLRPC.TL_messageMediaVenue getItem(int i) {
        if (i >= 0 && i < places.size()) {
            return places.get(i);
        }
        return null;
    }

    @Override
    public boolean isEnabled(RecyclerTmView.ViewHolder holder) {
        return true;
    }
}
