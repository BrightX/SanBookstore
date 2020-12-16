let layTipAddBook

let getData = function () {
    $.getJSON({
        url: getShopUrl,
        success: function (result) {
            if (result.shop) {
                shopApp.$data.shop = result.shop
                $.getJSON({
                    url: getOrdersUrl,
                    success: function (result1) {
                        shopApp.$data.orderList = result1.orderList
                    }
                })
                $.getJSON({
                    url: getBooksUrl,
                    success: function (result2) {
                        shopApp.$data.bookList = result2.bookList
                    }
                })
            }
        }
    })
}

let addBookSubmit = function (form) {
    let formData = new FormData(form);
    $.ajax({
        url: form.action,
        type: "POST",
        data: formData,
        contentType: false,
        cache: false,
        processData: false,
        beforeSend: function () {
            layer.msg("正在添加商品，请稍等。。。")
        },
        success: function () {
            layer.msg("商品添加成功")
            getData()
            if (layTipAddBook) {
                layer.close(layTipAddBook)
            }
        }
    })
    return false;
}

let doAddBook = function (addBookUrl) {
    layTipAddBook = layer.open({
        type: 1,
        title: false,
        shadeClose: false,
        shade: 0.25,
        area: ['600px', '500px'],
        content: `<form action="${addBookUrl}" method="post" enctype="multipart/form-data" onsubmit="addBookSubmit(this); return false;"
            id="add-book-form" style="padding: 10px">
        <div class="alert alert-warning text-center h5">添加商品</div>
        
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-prepend">
                    <label for="id-name" class="input-group-text">
                        <i class="fa fa-book fa-lg"></i>
                    </label>
                </div>
                <input type="text" class="form-control" name="name" id="id-name" maxlength="50" required
                       placeholder="请输入书名">
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-prepend">
                    <label for="id-info" class="input-group-text">
                        <i class="fa fa-info fa-lg"></i>
                    </label>
                </div>
                <textarea required class="form-control" id="id-info" placeholder="请输入图书简介" name="info"></textarea>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-prepend">
                    <label for="id-price" class="input-group-text">
                        <i class="fa fa-lg fa-dollar"></i>
                    </label>
                </div>
                <input type="number" id="id-price" class="form-control" required min="0.1" max="50000" step="0.1"
                       name="price" placeholder="请输入价格">
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-prepend">
                    <label for="image-upload" class="input-group-text">
                        <i class="fa fa-lg fa-image"></i>
                    </label>
                </div>
                <div class="custom-file">
                    <input type="file" name="image" id="image-upload" accept="image/*" required
                           class="custom-file-input">
                    <label class="custom-file-label" id="image-upload-label" for="image-upload">上传图片</label>
                </div>
            </div>
            <img src="" alt="" id="upload-img" class="img-fluid img-thumbnail" style="display: none">
            <script>
                let uploadImg = $("#upload-img")
                let uploadLabel = $("#image-upload-label")
                $("#image-upload").on("change", function () {
                    uploadImg.hide()
                    uploadImg[0].src = "";
                    uploadLabel.text("上传图片");
                    
                    let file = this.files[0];
                    uploadImg[0].src = URL.createObjectURL(file);
                    uploadImg.show()
                    uploadLabel.text(file.name);
                })
                $("#add-book-form").on("reset", function () {
                    uploadImg.hide()
                    uploadImg[0].src = "";
                    $("#image-upload-label").text("上传图片");
                })
            </script>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-prepend">
                    <label for="id-inventory" class="input-group-text">
                        <i class="fa fa-lg fa-truck"></i>
                    </label>
                </div>
                <input type="number" min="1" required id="id-inventory" class="form-control" name="inventory"
                       placeholder="请输入库存数量">
            </div>
        </div>

        <div class="form-group text-center">
            <input type="submit" class="btn btn-primary" value="上 架">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <input type="reset" class="btn btn-outline-secondary" value="重 置">
        </div>
    </form>
`,
    })
}

let layTipCashingBalance
let cashingBalance = function () {
    layTipCashingBalance = layer.open({
        type: 1,
        title: `<i class="fa fa-dollar" style="font-size: 1.5rem;color: gold;"></i>  商铺提现`,
        shadeClose: false,
        shade: 0.25,
        area: ['320px', '320px'],
        content: `<form action="${cashingBalanceUrl}" method="post" onsubmit="cashingBalanceSubmit(this); return false;"
              id="cashing-balance-form" style="padding: 10px">
            <div class="alert alert-info">账户余额：<span style="color: #F70000">￥ <span class="balance">${user.balance.toFixed(2)}</span></span></div>
            <div class="alert alert-info">可提现金额：<span style="color: #F70000">￥ <span class="balance">${shopApp.$data.shop.balance.toFixed(2)}</span></span></div>
            <div class="form-group">
                <div class="input-group input-group-lg">
                    <div class="input-group-prepend">
                        <label class="input-group-text" for="id-amount"><i class="fa fa-lg fa-dollar"></i></label>
                    </div>
                    <input type="hidden" name="next" value="${location.href}">
                    <input type="number" class="form-control" min="0.5" max="${shopApp.$data.shop.balance}" size="7" step="0.5" required
                           name="amount" id="id-amount" autocomplete="off"
                           placeholder="请输入提现金额">
                </div>
            </div>
            <div class="form-group text-center">
                <input type="submit" class="btn btn-primary" value="提 现">
                &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="reset" class="btn btn-outline-secondary" value="重 置">
            </div>
        </form>`,
    })
}
let cashingBalanceSubmit = function (form) {
    let formData = new FormData(form)
    $.ajax({
        url: form.action,
        type: "POST",
        data: {
            next: formData.get("next"),
            amount: formData.get("amount"),
        },
        beforeSend: function () {
            layer.msg("正在提现，请稍等。。。")
        },
        success: function (result) {
            if (result.status > 0) {
                layer.msg(`提现成功`)
                getData()
                getUserInfo(userInfoUrl)
            } else {
                layer.msg(result.msg)
            }
        },
        error: function () {
            layer.msg("400 提现失败")
        },
        complete: function () {
            if (layTipCashingBalance) {
                layer.close(layTipCashingBalance)
            }
        },
    })
    return false;
}

let toOrderDetailManage = function (order) {
    // ['待发货', '已发货', '已收货']
    let statusClass = 'warning'
    if (order.status === '已收货') {
        statusClass = 'success'
    } else if (order.status === '已发货') {
        statusClass = 'info'
    }
    layer.open({
        type: 1,
        title: `<i class="fa fa-calendar-o text-info" style="font-size: 25px;"></i>  商家订单管理`,
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
                    订单状态：<button class="btn btn-sm btn-${statusClass}" onclick="toDelivery(${order.id}, '${order.status}')">${order.status}</button>
                </p>
                <p>买家：<span class="text-info">${order.username}</span></p>
                <p>收货地址：<small>${order.receiveAddress}</small></p>
                <p>商品价格：<strong class="text-danger">￥${order.price.toFixed(2)}</strong></p>
                <p>商品数量：<strong class="text-danger">×${order.purchaseQuantity}</strong> 本</p>
                <p>付款金额：<strong class="text-danger">￥${order.paymentAmount.toFixed(2)}</strong></p>
                <p>商铺：<span>${order.shopName}</span></p>
                <p>发货地址：<small>${order.sendAddress}</small></p>
                <p>订单号：<strong>${order.orderNumber}</strong></p>
                <p>生成时间：${new Date(order.createTime).toLocaleString('zh-CN', {
            timeZone: 'Asia/Shanghai',
            hour12: false
        })}</p>
            </div>
        </div>`
    })
}

let toDelivery = function (orderId, status) {
    if (status === '待发货') {
        layer.alert('确认发货吗？', {
            title: '确认发货'
            , icon: 3 //0:感叹号 1：对号 2：差号 3：问号 4：小锁 5：哭脸 6：笑脸
            , time: 0 //不自动关闭
            , btn: ['确认发货', '取消']
            , yes: function (index) {
                $.ajax({
                    type: "POST",
                    url: deliveryUrl,
                    data: {
                        orderId,
                    },
                    cache: false,
                    success: function (msg) {
                        if (msg.status > 0) {
                            layer.alert('发货成功', {title: '发货成功', icon: 1});
                            getData()
                            location.reload()
                        } else {
                            layer.alert('发货失败，当前状态不能发货', {title: '发货失败', icon: 4});
                        }
                    },
                    error: function () {
                        layer.alert('发货失败，当前状态不能发货', {title: '发货失败', icon: 4});
                    }
                });
                layer.close(index);
            }
        });
    }
}
