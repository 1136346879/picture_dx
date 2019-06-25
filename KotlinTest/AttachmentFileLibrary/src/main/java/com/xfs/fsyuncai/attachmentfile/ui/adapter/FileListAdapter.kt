package com.xfs.fsyuncai.attachmentfile.ui.adapter

import android.content.Context
import com.xfs.fsyuncai.art.base.view.adapter.BaseRvAdapter
import com.xfs.fsyuncai.attachmentfile.R
import com.xfs.fsyuncai.attachmentfile.data.AddFileInfo
import java.util.ArrayList

class FileListAdapter(list: ArrayList<AddFileInfo>, cnt: Context)
    : BaseRvAdapter<AddFileInfo>(list, R.layout.item_myphone_file_list, cnt) {
    override fun onBindView(holder: Companion.BaseRvHolder, data: AddFileInfo) {
        holder.setText(R.id.item_file_name, data.name)
        holder.setText(R.id.item_file_size, "文档大小：${data.size}")
    }
}