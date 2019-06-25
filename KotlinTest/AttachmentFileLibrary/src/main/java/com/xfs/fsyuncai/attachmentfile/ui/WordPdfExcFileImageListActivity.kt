package com.xfs.fsyuncai.attachmentfile.ui

import android.annotation.SuppressLint
import android.content.Intent
import com.xfs.fsyuncai.art.base.common.ActiveResultDef
import com.xfs.fsyuncai.art.base.network.retrofit.ext.bind
import com.xfs.fsyuncai.art.base.utils.FastClickUtils
import com.xfs.fsyuncai.art.base.view.activity.BaseActivity
import com.xfs.fsyuncai.art.base.weiget.LoadingDialog
import com.xfs.fsyuncai.attachmentfile.R
import io.reactivex.Observable
import java.io.File

class WordPdfExcFileImageListActivity : BaseActivity(),
        WordPdfExcelFileImageListFragment.IBindFileListListener {

    private var fragment: WordPdfExcelFileImageListFragment? = null

    override fun resLayout(): Int {
        return R.layout.activity_word_pdf_excel_file;
    }

    override fun init() {
//
    }

    /**
     * 读取文件夹最外层文件
     */
    @SuppressLint("CheckResult")
    override fun readOutFile(filePath: String) {
        Observable.just(File(filePath)).bind(this)
                .flatMap { file ->
                    if (file.isDirectory) {
                        Observable.create<File> {
                            readFile(file.absolutePath)
                        }
                    } else {
                        Observable.just(file).filter {
                            val filename = file.name
                            filename.toLowerCase().endsWith(".xlsx")
                                    || filename.toLowerCase().endsWith(".xls")
                                    || filename.toLowerCase().endsWith(".pdf")
                                    || filename.toLowerCase().endsWith(".docx")
                                    || filename.toLowerCase().endsWith(".doc")
                        }
                    }
                }
                .toList()
                .flatMapObservable { list ->
                    Observable.create<List<File>> {
                        it.onNext(list)
                        it.onComplete()
                    }
                }
                .subscribe({ files ->
                    fragment?.getFiles(files)
                }, {
                    // onError
                    fragment?.readError(it.message)
                    LoadingDialog.dissmiss()
                }, {
                    // onCompleted
                    LoadingDialog.dissmiss()
                }, {
                    // onSubscriber
                    LoadingDialog.show(this, "")
                })

    }

    /**
     * 读取文件夹内所有文件
     */
    @SuppressLint("CheckResult")
    override fun readFile(filePath: String) {
        // 使用iterable遍历某个文件夹下的所有文件
        Observable.fromIterable(File(filePath).walk().asIterable())
                .bind(this)
                //过滤文件，只显示excel , word  和 pdf文档
                .filter { file ->
                    val filename = file.name
                    filename.toLowerCase().endsWith(".xlsx")
                            || filename.toLowerCase().endsWith(".xls")
                            || filename.toLowerCase().endsWith(".pdf")
                            || filename.toLowerCase().endsWith(".docx")
                            || filename.toLowerCase().endsWith(".doc")
                }
                // 当所有文件发送完成统一回调，返回list
                .toList()
                .flatMapObservable { list ->
                    Observable.create<List<File>> {
                        it.onNext(list)
                        it.onComplete()
                    }
                }
                .subscribe({ files ->
                    fragment?.getFiles(files)
                }, {
                    // onError
                    fragment?.readError(it.message)
                    LoadingDialog.dissmiss()
                }, {
                    // onCompleted
                    LoadingDialog.dissmiss()
                }, {
                    // onSubscriber
                    LoadingDialog.show(this, "")
                })
    }

    override fun logic() {
        if (fragment == null) {
            fragment = WordPdfExcelFileImageListFragment.newInstance()
        }
        supportFragmentManager.beginTransaction().add(
                R.id.container, fragment!!, WordPdfExcelFileImageListFragment::class.java.simpleName
        ).commit()
    }

    companion object {
        fun wordPdfExcFileListActivityResult(context: AttachmentFileFragment) {
            if (FastClickUtils.isAllowClick()!!) {
                val intent = Intent(context.activity, WordPdfExcFileImageListActivity::class.java)
                context.startActivityForResult(intent, ActiveResultDef.FILE_RESULT_CODE)
            }
        }
    }
}