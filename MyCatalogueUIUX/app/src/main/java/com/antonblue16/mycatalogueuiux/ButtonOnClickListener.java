package com.antonblue16.mycatalogueuiux;

import android.view.View;

public class ButtonOnClickListener implements View.OnClickListener
{
    private int position;
    private ItemButtonCallback itemButtonCallback;
    public ButtonOnClickListener(int position, ItemButtonCallback itemButtonCallback)
    {
        this.position = position;
        this.itemButtonCallback = itemButtonCallback;
    }

    @Override
    public void onClick(View view)
    {
        itemButtonCallback.onItemClicked(view, position);
    }

    public interface ItemButtonCallback
    {
        void onItemClicked(View view, int position);
    }
}
