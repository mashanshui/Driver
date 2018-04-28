package com.shenhesoft.driver.activity.task;

import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import com.shenhesoft.driver.R;
import com.shenhesoft.driver.adapter.PDetailsAdapter;
import com.shenhesoft.driver.base.BaseActivity;
import com.shenhesoft.driver.bean.MyOrderBean;
import com.shenhesoft.driver.bean.PDetailsRootItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class WayBillDetailActivity extends BaseActivity {

    @BindView(R.id.details_listView)
    ExpandableListView exListview;

    private MyOrderBean mOrderBean;
    private List<PDetailsRootItem> rootItems;

    @Override
    protected void initTitle() {
        setTitle("运单详情");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bill_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        mOrderBean = (MyOrderBean) getIntent().getSerializableExtra("myOrderBean");
        PDetailsAdapter adapter = new PDetailsAdapter(createtListData(mOrderBean), mcontext);
        exListview.setGroupIndicator(null);
        exListview.setAdapter(adapter);
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            exListview.expandGroup(i);
        }

        exListview.setOnGroupClickListener((parent, v, groupPosition, id) -> true);
    }

    private List<PDetailsRootItem> createtListData(MyOrderBean bean) {
        rootItems = new ArrayList<>();
//        运单状态
        if (bean.getStatus().equals("5")) {
            bean.setStatus("货位引导");
            createData1(bean);
        } else if (bean.getStatus().equals("6")) {
            bean.setStatus("等待回单");
            createData2(bean);
        } else if (bean.getStatus().equals("7")) {
            bean.setStatus("等待确认");
            createData2(bean);
        }
        return rootItems;
    }

    private void createData1(MyOrderBean bean) {
        //        项目类型（0 集装箱 1 散装） projectType
        if (bean.getProjectType().equals("0")) {
            bean.setProjectType("集装箱");
        } else if (bean.getProjectType().equals("1")) {
            bean.setProjectType("散装");
        }

        PDetailsRootItem item1 = new PDetailsRootItem("项目信息", R.drawable.icon_xiangmxx);
        item1.setChildItems(PDetailsRootItem.createChilds(
                new String[]{"项目编号：", "项目类型：", "发货企业", "收货企业", "分支机构：", "调度员：",
                        "创建时间：", "运单状态：", "更新时间："},
                new String[]{bean.getProjectCode(), bean.getProjectType(), bean.getSendCompany(), bean.getReceiptCompany(), bean.getBranchGroupName()
                        , bean.getUserDispatchName(), bean.getCreateDate(), bean.getStatus(), bean.getUpdateDate()}
        ));
        rootItems.add(item1);

        PDetailsRootItem item2 = new PDetailsRootItem("货物信息", R.drawable.icon_jizxxx);
        item2.setChildItems(PDetailsRootItem.createChilds(
                new String[]{"货物名称：", "货物规格：", "化验指标："},
                new String[]{bean.getCargoName(), bean.getSpecifications(), bean.getTestIndicators()}
        ));
        rootItems.add(item2);

        PDetailsRootItem item3 = new PDetailsRootItem("车辆信息", R.drawable.icon_chelxx);
        item3.setChildItems(PDetailsRootItem.createChilds(
                new String[]{"承运车辆：", "车辆类型：", "驾驶员：", "联系方式："},
                new String[]{bean.getCarPlateNumber(), bean.getCarType(), bean.getDriverName(), bean.getDriverPhone()}
        ));
        rootItems.add(item3);

        //if(..集装=true..){}
        if (bean.getProjectType().equals("集装箱")) {
            PDetailsRootItem item4 = new PDetailsRootItem("集装箱信息", R.drawable.icon_jizxxx);
            item4.setChildItems(PDetailsRootItem.createChilds(
                    new String[]{"箱号：", "箱号："},
                    new String[]{bean.getContainerNumber1(), bean.getContainerNumber2()}
            ));
            rootItems.add(item4);
        }

        PDetailsRootItem item5 = new PDetailsRootItem("收发货信息", R.drawable.icon_chelxx);
        item5.setChildItems(PDetailsRootItem.createChilds(
                new String[]{"发货单位：", "取货地址：", "发货毛重：", "发货皮重：", "发货净重：", "发货净重：",
                        "收货单位：", "运抵地址：", "货场：", "货位：", "到货毛重：", "到货皮重：", "到货净重：",
                        "到货净重："},
                new String[]{bean.getSendCompany(), bean.getPickupPlace(), bean.getSendGross(), bean.getSendTare(),
                        bean.getContainerOneSendNet(), bean.getContainerTwoSendNet(),
                        bean.getReceiptCompany(),  bean.getArrivePlace(), bean.getDistributionCargoPlace(),
                        bean.getDistributionCargoSite(), bean.getReceiptGross(), bean.getReceiptTare(),
                        bean.getContainerOneReceiptNet(), bean.getContainerTwoReceiptNet()}
        ));
        Log.e(TAG, "createData1:----------- "+ bean.getSendGross()+bean.getSendTare());
        rootItems.add(item5);

    }

    private void createData2(MyOrderBean bean) {
        PDetailsRootItem item1 = new PDetailsRootItem("项目信息", R.drawable.icon_xiangmxx);
        item1.setChildItems(PDetailsRootItem.createChilds(
                new String[]{"项目编号：", "货物品名：", "发货单位", "收货单位", "运输费用：", "扣损系数：",
                        "扣损金额：", "对账时间：", "结算时间：", "油气卡领取时间："},
                new String[]{bean.getProjectCode(), bean.getCargoName(), bean.getSendCompany(), bean.getReceiptCompany(), bean.getShortBargeCost()
                        , bean.getDeductionRate(), bean.getDeductionPrice(), bean.getCheckingAuditDate()
                        , bean.getSettleAuditDate(), bean.getSuppliesExecuteDate()}
        ));
        rootItems.add(item1);

        PDetailsRootItem item2 = new PDetailsRootItem("对账信息", R.drawable.icon_jizxxx);
        item2.setChildItems(PDetailsRootItem.createChilds(
                new String[]{"打包车次：", "打包车辆：", "合计运费：", "收入现金：", "收入油气：", "收款人："
                        , "支付方式：", "油气卡领取人：", "领取方式："},
                new String[]{bean.getPackTruckDegree(), bean.getPackTruckNum(), bean.getFreightChargeAmount(), bean.getCashAmount(),bean.getSuppliesAmount()
                        , bean.getReceiveUserName(), bean.getCashPayType(), bean.getReceiveUserName(), bean.getSuppliesReceiveType()}
        ));
        rootItems.add(item2);

        //遍历数组，显示所有对账单
        int num = 0;
        List<MyOrderBean.ShortOrderFinancesBean> financesBeanList = bean.getShortOrderFinances();
        if (financesBeanList==null || financesBeanList.isEmpty()) {
            return;
        }
        for (int i = 0; i < financesBeanList.size(); i++) {
            num++;
            MyOrderBean.ShortOrderFinancesBean financesBean = financesBeanList.get(i);
            PDetailsRootItem item = new PDetailsRootItem("对账详情" + num, R.drawable.icon_chelxx);
            item.setChildItems(PDetailsRootItem.createChilds(
                    new String[]{"运单号：", "运输时间：", "运输费用：", "运费补贴：", "扣损金额：", "运费核计："},
                    new String[]{financesBean.getOrderCodeX(), financesBean.getOrderCreateDate(), financesBean.getFShortBargeCost()
                            , financesBean.getFSubsidy(), financesBean.getBuckleFigure(), financesBean.getShouldPayFigure()}
            ));
            rootItems.add(item);
        }
    }
}
