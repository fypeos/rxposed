package hepta.rxposed.manager.fragment.InjectPlug;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import hepta.rxposed.manager.R;
import hepta.rxposed.manager.fragment.base.AppItemInfo;


public class PlugListAdapter extends BaseQuickAdapter<AppItemInfo, BaseViewHolder> implements LoadMoreModule {

    public Boolean SELECT = false;

    public PlugListAdapter(int layoutResId, Boolean inject) {
        super(layoutResId);
        SELECT = inject;
    }


    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder,AppItemInfo item) {
        if (item == null) {
            return;
        }

        baseViewHolder.setText(R.id.app_name, item.getAppName());
        baseViewHolder.setText(R.id.app_packageName, item.getPackageName());
        baseViewHolder.setImageDrawable(R.id.app_icon,item.getIcon());
        CheckBox checkBox = baseViewHolder.findView(R.id.btn_check_app);
        if(SELECT){
            checkBox.setVisibility(View.VISIBLE);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    item.setEnable(isChecked);
                }
            });
            baseViewHolder.findView(R.id.btn_open_app).setVisibility(View.INVISIBLE);
        }else {
            checkBox.setVisibility(View.INVISIBLE);
            baseViewHolder.findView(R.id.btn_open_app).setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int getDefItemViewType(int position) {   //不写这个容易导致item混乱
        return position;
    }

}