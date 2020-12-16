let toOrderDetail = function (order) {
    // ['待发货', '已发货', '已收货']
    let statusClass = 'warning'
    if (order.status === '已收货') {
        statusClass = 'success'
    } else if (order.status === '已发货') {
        statusClass = 'info'
    }
    layer.open({
        type: 1,
        title: `<i class="fa fa-calendar-o text-info" style="font-size: 25px;"></i>  订单详情`,
        shadeClose: false,
        shade: 0.25,
        area: ['850px', '550px'],
        content: `<div style="display: flex;">
            <div style="width: 50%;padding: 10px;margin-right: 10px;">
                <img src="${order.image ? order.image : demoImg}" alt="" class="img-fluid img-thumbnail">
            </div>
            <div style="width: 42%;">
                <h4>${order.bookName}</h4>
                <p>
                    订单状态：<button class="btn btn-sm btn-${statusClass}" onclick="toReceive(${order.id}, '${order.status}')">${order.status}</button>
                </p>
                <p>收货地址：<small>${order.receiveAddress}</small></p>
                <p>商品价格：<strong class="text-danger">￥${order.price.toFixed(2)}</strong></p>
                <p>商品数量：<strong class="text-danger">×${order.purchaseQuantity}</strong> 本</p>
                <p>付款金额：<strong class="text-danger">￥${order.paymentAmount.toFixed(2)}</strong></p>
                <p>商铺：<span>${order.shopName}</span></p>
                <p>发货地址：<small>${order.sendAddress}</small></p>
                <p>订单号：<strong>${order.orderNumber}</strong></p>
                <p>生成时间：${new Date(order.createTime).toLocaleString('zh-CN', {timeZone:'Asia/Shanghai', hour12:false})}</p>
            </div>
        </div>`
    })
}

let toReceive = function (orderId, status) {
    if (status === '已发货') {
        layer.alert('确认收货吗？', {
            title: '确认收货'
            , icon: 3 //0:感叹号 1：对号 2：差号 3：问号 4：小锁 5：哭脸 6：笑脸
            , time: 0 //不自动关闭
            , btn: ['确认收货', '取消']
            , yes: function (index) {
                $.ajax({
                    type: "POST",
                    url: receiveUrl,
                    data: {
                        orderId,
                    },
                    cache: false,
                    success: function (msg) {
                        if (msg.status > 0) {
                            layer.alert('收货成功', {title: '收货成功', icon: 1});
                            location.reload()
                        } else {
                            layer.alert('收货失败，当前状态不能收货', {title: '收货失败', icon: 4});
                        }
                    },
                    error: function () {
                        layer.alert('收货失败，当前状态不能收货', {title: '收货失败', icon: 4});
                    }
                });
                layer.close(index);
            }
        });
    }
}
