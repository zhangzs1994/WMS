package com.ycsx.www.wms.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ycsx.www.wms.R;

/**
 * Created by ZZS_PC on 2017/5/11.
 */
public class LogRecyclerHolder extends RecyclerView.ViewHolder {
    public TextView log_user,log_event,log_date;

    public LogRecyclerHolder(View itemView) {
        super(itemView);
        log_user= (TextView) itemView.findViewById(R.id.log_user);
        log_event= (TextView) itemView.findViewById(R.id.log_event);
        log_date= (TextView) itemView.findViewById(R.id.log_date);
    }
}
