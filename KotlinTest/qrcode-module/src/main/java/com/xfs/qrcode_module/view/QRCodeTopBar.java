package com.xfs.qrcode_module.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xfs.qrcode_module.R;


/**
 * @author yangyi  2018年08月05日13:03:15
 *         <p>
 *         wechat: yangyi_451686712
 */
public class QRCodeTopBar extends RelativeLayout {

    private TextView topNameText;

    public interface QRCodeTopBarClickListener {
        /**
         * 点左
         */
        void startClick();

        /**
         * 点右
         */
        void endClick();
    }

    private QRCodeTopBarClickListener qrCodeTopBarClickListener;

    public void setQrCodeTopBarClickListener(QRCodeTopBarClickListener qrCodeTopBarClickListener) {
        this.qrCodeTopBarClickListener = qrCodeTopBarClickListener;
    }

    public QRCodeTopBar(Context context) {
        super(context);
        initView();
    }

    public QRCodeTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public QRCodeTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.view_qrcode_top_bar, this, true);
        view.findViewById(R.id.topBack).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (qrCodeTopBarClickListener != null) {
                    qrCodeTopBarClickListener.startClick();
                }
            }
        });
        view.findViewById(R.id.topAlbumText).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (qrCodeTopBarClickListener != null) {
                    qrCodeTopBarClickListener.endClick();
                }
            }
        });
        topNameText = view.findViewById(R.id.topNameText);
    }

    public void setTopName(String topName) {
        topNameText.setVisibility(VISIBLE);
        topNameText.setText(topName);
    }
}
