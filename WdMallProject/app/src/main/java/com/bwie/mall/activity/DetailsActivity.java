package com.bwie.mall.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.mall.R;
import com.bwie.mall.adapter.DetailsAdapter;
import com.bwie.mall.bean.DetailsBean;
import com.bwie.mall.bean.QueryCartBean;
import com.bwie.mall.bean.ShopDetails;
import com.bwie.mall.bean.ShopQueryListBean;
import com.bwie.mall.bean.SyncShopCarBean;
import com.bwie.mall.presenter.DetailsPresenter;
import com.bwie.mall.utils.DensityUtil;
import com.bwie.mall.utils.StatusBarUtil;
import com.bwie.mall.view.DetailsView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/20 13:38:25
 * @Description:
 */
public class DetailsActivity extends BaseActivity<DetailsPresenter> implements DetailsView {

    private String commodityId;
    @BindView(R.id.l_recycler_view)
    RecyclerView lrecyclerView;
    @BindView(R.id.left)
    TextView left;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.titlel)
    TextView titlel;
    @BindView(R.id.titler)
    TextView titler;
    @BindView(R.id.button)
    LinearLayout button;
    @BindView(R.id.right)
    TextView right;
    @BindView(R.id.icon)
    ImageView icon;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.btn_add)
    ImageButton btnAdd;
    @BindView(R.id.btn_buy)
    ImageButton btnBuy;
    private float totaldy;
    private float mRecyclerFactor;
    private List<DetailsBean> list;
    private int item1 = 0;
    private int item2 = 0;
    private int item3 = 0;
    private LinearLayoutManager manager;
    private Resources res;
    private SharedPreferences sp_login;
    private String sessionId;
    private String userId;
    private ShopDetails.ResultBean buyBean;

    @Override
    protected void onResume() {
        super.onResume();
        sp_login = getSharedPreferences("login", Context.MODE_PRIVATE);
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_details;
    }

    @Override
    public void initView() {

        EventBus.getDefault().register(this);
    }

    @Override
    public DetailsPresenter getPresenter() {
        presenter = new DetailsPresenter(this);
        return presenter;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getEventBusData(String id) {
        //Log.i("xxx", "getEventBusData: " + id);
        if (!TextUtils.isEmpty(id)) {
            //参数不为空赋值
            commodityId = id;
        }
    }

    @Override
    public void initData() {
        //进行网络请求
        presenter.onRelated(commodityId);
        setMyBgView();
        getMyTitle();
        //添加购物车按钮点击事件
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionId = sp_login.getString("sessionId", null);
                userId = sp_login.getString("userId", null);
                /**
                 * 点击添加购物车按钮判断是否登录
                 * 如若没登录
                 * 弹框提示是否去登陆
                 * 去登录：跳转到登录页面登录
                 * 取消：
                 */
                if (TextUtils.isEmpty(sessionId) && TextUtils.isEmpty(userId)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this);
                    builder.setTitle("提示!");
                    builder.setMessage("登录后可添加商品到购物车");
                    builder.setPositiveButton("去登录", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //跳转登录
                            startActivity(new Intent(DetailsActivity.this, LoginActivity.class));
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();
                } else {
                    //添加(同步)购物车方法
                    //先查询
                    queryCart(sessionId, userId);
                    //Log.i("xxx", "onClick: else");
                }
            }
        });

        //立刻购买
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionId = sp_login.getString("sessionId", null);
                userId = sp_login.getString("userId", null);
                /**
                 * 点击立刻购买按钮判断是否登录
                 * 如若没登录
                 * 弹框提示是否去登陆
                 * 去登录：跳转到登录页面登录
                 * 取消：
                 */
                if (TextUtils.isEmpty(sessionId) && TextUtils.isEmpty(userId)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this);
                    builder.setTitle("提示!");
                    builder.setMessage("登录后可添加商品到购物车");
                    builder.setPositiveButton("去登录", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //跳转登录
                            startActivity(new Intent(DetailsActivity.this, LoginActivity.class));
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();
                } else {
                    // EventBus传值buyBean
                    EventBus.getDefault().postSticky(buyBean);
                    startActivity(new Intent(DetailsActivity.this, NewMenuActivity.class));
                }
            }
        });
    }

    //查询购物车方法
    private void queryCart(String sessionId, String userId) {
        presenter.queryCart(sessionId, userId);
    }


    private void setMyBgView() {

        list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            DetailsBean bean = new DetailsBean();
            bean.setType(i + 1);
            list.add(bean);
        }
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item1 != 0) {
                    lrecyclerView.scrollBy(0, (int) -totaldy);
                }
            }
        });
        titlel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item2 != 0) {
//                    判断滑动距离是否超过商品
                    if (totaldy > item1)

                        lrecyclerView.scrollBy(0, (int) -(totaldy - item1) + 20);
                    else
                        lrecyclerView.scrollBy(0, (int) (item1 - totaldy) + 20);

                }
            }
        });
        titler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item3 != 0) {
                    lrecyclerView.scrollBy(0, item2);
                }
            }
        });
        //        设置渐变的主要代码
        lrecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recycler, int dx, int dy) {
                super.onScrolled(recycler, dx, dy);

                //滑动的距离
                totaldy += dy;
                if (item2 != 0 && item1 != 0 && item3 != 0) {
                    if (totaldy < item1) {
                        title.setTextColor(res.getColor(R.color.orange));
                        titlel.setTextColor(res.getColor(R.color.black));
                        titler.setTextColor(res.getColor(R.color.black));
                    } else if (totaldy > item1 && totaldy < item2) {
                        titlel.setTextColor(res.getColor(R.color.orange));
                        title.setTextColor(res.getColor(R.color.black));
                        titler.setTextColor(res.getColor(R.color.black));
                    } else if (totaldy > item2) {
                        titler.setTextColor(res.getColor(R.color.orange));
                        title.setTextColor(res.getColor(R.color.black));
                        titlel.setTextColor(res.getColor(R.color.black));
                    }
                }
                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
                if (totaldy <= mRecyclerFactor) {
//                    如果在显示图片中显示圆图标
//                    算出透明度
                    float scale = (float) totaldy / mRecyclerFactor;
                    float alpha = scale * 255;

                    if (alpha < 160) {
//                        如果透明度小于160设置为顶部是图片
                        button.setVisibility(View.GONE);
                        StatusBarUtil.setTranslucentForImageView(DetailsActivity.this, (int) alpha, titleBar);
                    } else {
                        button.setVisibility(View.VISIBLE);
                        StatusBarUtil.setColor(DetailsActivity.this, Color.argb((int) alpha, 255, 255, 255));
                    }
                    titleBar.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                } else {
//                   已经不显示图片
                    titleBar.setBackgroundColor(Color.parseColor("#ffffff"));
                    button.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void getMyTitle() {
        res = getResources();
        StatusBarUtil.setTranslucentForImageView(this, 0, titleBar);
        left.setBackgroundResource(R.mipmap.back_b);
        //right.setBackgroundResource(R.mipmap.add);
        right.setVisibility(View.GONE);
        icon.setVisibility(View.VISIBLE);
        // 图片的高度-状态栏的高度
        mRecyclerFactor = (DensityUtil.dp2px(this, 180.0F) - DensityUtil.getStatusBarHeight(this));

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void getDetailsData(ShopDetails.ResultBean result) {
        // Log.i("xxx", "getDetailsData: "+result.getCategoryId());
        //获取到请求成功集合进行赋值
        if (result != null) {
            buyBean = result;
            setAdapter(list, result);
        }
    }

    @Override
    public void getQueryCarData(QueryCartBean queryCartBean) {
        List<QueryCartBean.ResultBean> result = queryCartBean.getResult();
        // Log.i("xxx", "getQueryCarData: "+result.toString());
        //创建添加购物车的集合
        List<ShopQueryListBean> listBeans = new ArrayList<>();
        //拿到查询购物车的集合遍历数据添加到（添加购物车的集合）
        if (result.size() != 0) {
            /**
             * 先遍历修改对应的数量
             */
            for (int i = 0; i < result.size(); i++) {
                if (Integer.valueOf(commodityId) == result.get(i).getCommodityId()) {
                    int count = result.get(i).getCount();
                    count++;
                    result.get(i).setCount(count);
                    //如果遍历完毕没有相同的商品，就把当前的商品加入到购物车
                    break;
                } else if (result.size() - 1 == i) {
                    result.add(new QueryCartBean.ResultBean(Integer.parseInt(commodityId), 1));
                    break;
                }
            }
            /**
             * 在遍历到同步购物车集合
             */
            for (int i = 0; i < result.size(); i++) {
                listBeans.add(new ShopQueryListBean(result.get(i).getCommodityId(), result.get(i).getCount()));
            }
        } else {
            listBeans.add(new ShopQueryListBean(Integer.parseInt(commodityId), 1));
        }
        addShopList(listBeans);
    }

    @Override
    public void getSyncShopCar(SyncShopCarBean syncShopCarBean) {
        if (syncShopCarBean != null) {
            String status = syncShopCarBean.getStatus();
            String message = syncShopCarBean.getMessage();
            if (status.equals(0000)) {
                Toast.makeText(DetailsActivity.this, message, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DetailsActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 同步购物车方法
     *
     * @param list
     */
    private void addShopList(List<ShopQueryListBean> list) {
        String s = new Gson().toJson(list);
       // Log.i("xxx", "addShopList: " + s);
        presenter.getSyncShopCart(userId, sessionId, s);
    }

    private void setAdapter(List<DetailsBean> list, ShopDetails.ResultBean detailsResult) {
        lrecyclerView.setNestedScrollingEnabled(false);
        DetailsAdapter adapter = new DetailsAdapter(this, detailsResult);
        adapter.setDataList(list);
        LRecyclerViewAdapter adapter1 = new LRecyclerViewAdapter(adapter);
        View headView = View.inflate(this, R.layout.details_head, null);
        adapter1.addHeaderView(headView);
        manager = new LinearLayoutManager(this);
        lrecyclerView.setLayoutManager(manager);
        lrecyclerView.setAdapter(adapter1);
        /*lrecyclerView.setPullRefreshEnabled(false);
        lrecyclerView.setLoadMoreEnabled(false);*/
        adapter.setListener(new DetailsAdapter.OnItemHeightListener() {
            @Override
            public void setOnItemHeightListener(int height, int type) {
                if (height != 0) {
                    if (type == 1001) {
                        item1 = (int) (height + mRecyclerFactor);
                    } else if (type == 1002) {
                        item2 = item1 + (height - DensityUtil.getWidth(DetailsActivity.this));
                    } else {
                        item3 = item2 + height;
                    }
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
