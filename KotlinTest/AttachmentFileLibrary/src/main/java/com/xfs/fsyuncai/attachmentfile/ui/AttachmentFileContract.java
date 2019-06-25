package com.xfs.fsyuncai.attachmentfile.ui;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.xfs.fsyuncai.art.base.mvp.BasePresenter;
import com.xfs.fsyuncai.art.base.mvp.BaseView;
import com.xfs.fsyuncai.art.base.network.retrofit.exception.ApiErrorModel;
import com.xfs.fsyuncai.attachmentfile.data.OrderAttachmentListBean;
import com.xfs.fsyuncai.attachmentfile.data.OrderQuerySucessFile;

import java.util.ArrayList;

/**
 * Created by kangf on 2018/8/18.
 */
public interface AttachmentFileContract {

    interface Presenter extends BasePresenter {
        void getDetail(String jsonData);

        void uploadFile(String path);

        void bindFileToOrder(String path, ArrayList<OrderAttachmentListBean> localList);

        void deleteFileFromOrder(ArrayList<OrderAttachmentListBean> deleteList, String orderId);
    }

    interface View extends BaseView<Presenter> {

        RxAppCompatActivity activity();

        void sucessQuery(OrderQuerySucessFile orderQuerySucessFile);

        void uploadSucess(OrderAttachmentListBean orderAttachmentListBean);

        void uploadError(int statusCode, ApiErrorModel apiErrorModel);

        void bindSucess();

        void deleteSucess();
    }
}
