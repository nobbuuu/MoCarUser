package com.dream.moka.IM.im.adapt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.IM.im.bean.Message;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.R;
import com.dream.moka.Utils.GlidUtils;
import com.dream.moka.Utils.StringUtil;

import java.util.List;

/**
 * 聊天界面adapter
 */
public class ChatAdapter extends ArrayAdapter<Message> {

    private int resourceId;
    private View view;
    private ViewHolder viewHolder;
    private OnLeftIconClick OnLeftIconClick;

    public void setOnLeftIconClick(ChatAdapter.OnLeftIconClick onLeftIconClick) {
        OnLeftIconClick = onLeftIconClick;
    }

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public ChatAdapter(Context context, int resource, List<Message> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    private String leftIcon = "";
    public void setHeadIcon(String leftIcon){
        this.leftIcon = leftIcon;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.leftAvatar = (ImageView) view.findViewById(R.id.leftAvatar);
            viewHolder.rightAvatar = (ImageView) view.findViewById(R.id.rightAvatar);
            viewHolder.leftMessage = (RelativeLayout) view.findViewById(R.id.leftMessage);
            viewHolder.rightMessage = (RelativeLayout) view.findViewById(R.id.rightMessage);
            viewHolder.leftPanel = (RelativeLayout) view.findViewById(R.id.leftPanel);
            viewHolder.rightPanel = (RelativeLayout) view.findViewById(R.id.rightPanel);
            viewHolder.sending = (ProgressBar) view.findViewById(R.id.sending);
            viewHolder.error = (ImageView) view.findViewById(R.id.sendError);
            viewHolder.sender = (TextView) view.findViewById(R.id.sender);
            viewHolder.rightDesc = (TextView) view.findViewById(R.id.rightDesc);
            viewHolder.systemMessage = (TextView) view.findViewById(R.id.systemMessage);
            view.setTag(viewHolder);
        }

        //设置数据
        if (CommonAction.getUserHeadUrl().contains("http")){
            GlidUtils.LoadImgForUrl(MyApplication.getInstance(), CommonAction.getUserHeadUrl(),viewHolder.rightAvatar);
        }else {
            GlidUtils.LoadImgForUrl(MyApplication.getInstance(), Const.BannerUrl+CommonAction.getUserHeadUrl(),viewHolder.rightAvatar);
        }
        if (StringUtil.NoNullOrEmpty(leftIcon)){
            GlidUtils.LoadImgForUrl(MyApplication.getInstance(), Const.BannerUrl+leftIcon,viewHolder.leftAvatar);
        }
        if (position < getCount()) {
            final Message data = getItem(position);
            data.showMessage(viewHolder, getContext());
        }
        viewHolder.leftAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (OnLeftIconClick != null) {
                    OnLeftIconClick.onClick();
                }
            }
        });
        return view;
    }


    public class ViewHolder {
        public RelativeLayout leftMessage;
        public RelativeLayout rightMessage;
        public RelativeLayout leftPanel;
        public RelativeLayout rightPanel;
        public ProgressBar sending;
        public ImageView error;
        public TextView sender;//left name
        public TextView systemMessage;
        public TextView rightDesc;
        public ImageView leftAvatar;
        public ImageView rightAvatar;
    }

    public interface OnLeftIconClick {
        void onClick();
    }
}
