package com.example.administrator.kotlintest.picture;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.administrator.kotlintest.R;
import com.example.administrator.kotlintest.ui.fragment.BaseAppFragment;
import com.example.baselibrary.widgets.ToastUtilKt;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.xfs.fsyuncai.bridge.retrofit.ApiConstants;
import com.xfs.fsyuncai.bridge.retrofit.callback.HttpOnNextListener;
import com.xfs.fsyuncai.bridge.retrofit.exception.ApiErrorModel;
import com.xfs.fsyuncai.bridge.retrofit.http.HttpManager;
import com.xfs.fsyuncai.bridge.retrofit.service.OrderService;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PictureUploadFragment extends BaseAppFragment implements ImagePickerAdapter.OnRecyclerViewItemClickListener {

    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 8;               //允许选择图片最大数


    @Override
    public int layoutResId() {
        return R.layout.activity_picture_upload;
    }

    public static PictureUploadFragment newInstance() {
        PictureUploadFragment fragment = new PictureUploadFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void init() {
//
    }

    @Override
    public void logic() {

        getViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!selImageList.isEmpty()){
                    uploadImage(selImageList);
                }else{
                    Toast.makeText(getActivity(),"请选择照片",Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

        );
        //最好放到 Application oncreate执行
        initImagePicker();
        initWidget();
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                            //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setMultiMode(false);                      //多选
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    private void initWidget() {
        RecyclerView recyclerView = getViewById(R.id.recyclerView);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(getActivity(), selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(getActivity(), R.style.transparentFrameWindowStyle, listener, names);
        if (!getActivity().isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent = new Intent(getActivity(), ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(getActivity(), ImageGridActivity.class);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }
                    }
                }, names);
//                showDialog((parent, view1, position1, id) -> {
//                    switch (position1) {
//                        case 0: // 直接调起相机
//                            //打开选择,本次允许选择的数量
//                            ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
//                            Intent intent = new Intent(getActivity(), ImageGridActivity.class);
//                            intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
//                            startActivityForResult(intent, REQUEST_CODE_SELECT);
//                            break;
//                        case 1:
//                            //打开选择,本次允许选择的数量
//                            ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
//                            Intent intent1 = new Intent(getActivity(), ImageGridActivity.class);
//                            startActivityForResult(intent1, REQUEST_CODE_SELECT);
//                            break;
//                        default:
//                            break;
//                    }
//                }, names);
                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(getActivity(), ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
    }

    private void uploadImage(ArrayList<ImageItem> pathList) {
//        Map<String, File> files = new HashMap<>();
//        for (int i = 0; i < pathList.size(); i++) {
//            String newPath = BitmapUtils.compressImageUpload(pathList.get(i).path);
//            files.put(pathList.get(i).name + i, new File(newPath));
//        }
        uploadFile(pathList);
    }

    private HttpManager httpManager = HttpManager.instance();

    private void uploadFile(ArrayList<ImageItem> pathList) {
        File file = new File(BitmapUtils.compressImageUpload(pathList.get(0).path));
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        httpManager.doHttpDeal(getActivity(), httpManager.createService(OrderService.class, ApiConstants.UPLOAD_BASE_URL).pictureSearch(body),
                new HttpOnNextListener() {
                    @Override
                    public void onNext(@NotNull String json) {
                        ToastUtilKt.INSTANCE.showCustomToast("Image upload sucessed :"+json.toString());
                    }
                    @Override
                    public void onError(int statusCode, @Nullable ApiErrorModel apiErrorModel) {
                        super.onError(statusCode, apiErrorModel);
                        ToastUtilKt.INSTANCE.showCustomToast("Image upload failed");
                    }
                },true);
    }
}
