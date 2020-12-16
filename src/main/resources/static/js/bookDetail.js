let layTipToBuy
let buyBookForm
let toBuy = function (book, shop) {
    if (book.inventory < 1) {
        return false
    }
    layTipToBuy = layer.open({
        type: 1,
        shadeClose: false,
        title: false,
        shade: 0.25,
        area: ['800px', '500px'],
        content: `<form action="${buyBookUrl}" method="post" onsubmit="buyBookSubmit(this); return false;"
                id="buy-book-form" style="padding: 10px">
            <div class="alert alert-danger text-center h5">确认订单</div>
            
            <div class="alert alert-secondary" style="padding: .5rem; display: flex;">
                <img src="${book.image}" alt="" class="img-fluid img-thumbnail" style="height: 100px;margin-right: 10px;">
                <div style="width: calc(100% - 110px);">
                    <h6>${book.name}</h6>
                    <p style="width:99%;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;" class="small">${book.info}</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <span>
                            <strong class="text-danger">￥${book.price.toFixed(2)}</strong><br>
                            购买数量：<strong>×{{purchaseQuantity}}</strong>&nbsp;&nbsp;&nbsp;
                            金额：<strong class="text-danger">￥{{paymentAmount.toFixed(2)}}</>
                        </span>
                        
                        <div class="input-group input-group-sm bg-light" style="width: 150px" title="购买数量">
                            <div class="input-group-prepend">
                                <button class="btn btn-outline-danger" v-on:click="minus()" type="button"><i class="fa fa-minus"></i></button>
                            </div>
                            <input type="number" min="1" class="form-control" v-model="purchaseQuantity" required>
                            <div class="input-group-append">
                                <button class="btn btn-outline-danger" v-on:click="add()" type="button"><i class="fa fa-plus"></i></button>
                            </div>
                        </div>
                    </div> 
                </div>
            </div>
            
            <input type="hidden" name="purchaseQuantity" v-bind:value="purchaseQuantity">
            <input type="hidden" name="bookId" value="${book.id}">
            
            <div class="alert alert-info">
                商铺：<strong>${shop.name}</strong><br>
                发货地址：<strong>${shop.sendAddress}</strong>
            </div>
            
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-prepend">
                        <label for="id-receiveAddress" class="input-group-text">
                            <i class="fa fa-envelope-o fa-lg"></i>
                        </label>
                    </div>
                    <textarea required class="form-control" id="id-receiveAddress" placeholder="请输入收货地址"
                     title="请输入收货地址" name="receiveAddress"></textarea>
                </div>
            </div>
            
            <div class="form-group text-right">
                <input type="submit" id="buy-submit-btn" class="btn btn-danger" value="提交订单">
            </div>
            
        </form>
        
        <script>
            buyBookForm = new Vue({
                el: '#buy-book-form',
                data: {
                    paymentAmount: book.price,
                    purchaseQuantity: 1,
                },
                watch: {
                    purchaseQuantity: function () {
                        this.purchaseQuantity = parseInt(this.purchaseQuantity) || 1
                        if (this.purchaseQuantity < 1) {
                            this.purchaseQuantity = 1
                        }
                        if (this.purchaseQuantity > book.inventory) {
                            this.purchaseQuantity = book.inventory
                        }
                        this.paymentAmount = this.purchaseQuantity * book.price
                        $('#buy-submit-btn').removeAttr('disabled')
                    },
                },
                methods: {
                    add: function () {
                        this.purchaseQuantity++
                    },
                    minus: function () {
                        this.purchaseQuantity--
                    },
                },
            })
        </script>
        `,

    })
}

let buyBookSubmit = function (form) {
    $('#buy-submit-btn').attr('disabled', 'disabled')
    let formData = new FormData(form)
    let paymentAmount = formData.get('purchaseQuantity') * book.price
    if (paymentAmount > user.balance) {
        layer.msg('当前账户余额不足')
        return false
    } else {
        $.ajax({
            url: form.action,
            type: "POST",
            data: formData,
            contentType: false,
            cache: false,
            processData: false,
            before: function (){
                layer.msg("正在购买商品，请稍等。。。")
            },
            success: function (result) {
                if (result.status > 0) {
                    layer.msg(result.msg)
                    layer.close(layTipToBuy)
                    location.href = ctx + result.next
                } else {
                    layer.msg(result.msg)
                }
            },
            complete: function () {
                $('#buy-submit-btn').removeAttr('disabled')
            },
        })
    }
    return false;
}

let layerTipShopCartAdd
let shopCartAddForm
let toShopCart = function () {
    layerTipShopCartAdd = layer.open({
        type: 1,
        title: false,
        shadeClose: false,
        shade: 0.25,
        area: ['300px', '220px'],
        content: ` <form action="${shopCartAddUrl}" method="post" onsubmit="shopCartAddSubmit(this); return false;"
                            id="shop-cart-add-form" style="padding: 10px">
                        <div class="alert alert-danger text-center h5">加入购物车</div>
                        
                        <div class="alert alert-info">
                            商品数量：
                            <div class="input-group input-group-sm bg-light" style="width: 150px;display: inline-flex;" title="商品数量">
                                <div class="input-group-prepend">
                                    <button class="btn btn-outline-danger" v-on:click="minus()" type="button"><i class="fa fa-minus"></i></button>
                                </div>
                                <input type="hidden" name="bookId" value="${bookId}">
                                <input type="number" min="1" name="quantity" class="form-control" v-model="purchaseQuantity" required>
                                <div class="input-group-append">
                                    <button class="btn btn-outline-danger" v-on:click="add()" type="button"><i class="fa fa-plus"></i></button>
                                </div>
                            </div>
                        </div>
                        
                        <div class="form-group text-right">
                            <input type="submit" class="btn btn-sm btn-danger" value="确认加入">
                            <input type="button" class="btn btn-sm btn-info" onclick="layer.close(layerTipShopCartAdd)" value="取 消">
                        </div>
                        
                    </form>
                    
                    <script>
                        shopCartAddForm = new Vue({
                            el: '#shop-cart-add-form',
                            data: {
                                purchaseQuantity: 1,
                            },
                            watch: {
                                purchaseQuantity: function () {
                                    this.purchaseQuantity = parseInt(this.purchaseQuantity) || 1
                                    if (this.purchaseQuantity < 1) {
                                        this.purchaseQuantity = 1
                                    }
                                    if (this.purchaseQuantity > book.inventory) {
                                        this.purchaseQuantity = book.inventory
                                    }
                                },
                            },
                            methods: {
                                add: function () {
                                    this.purchaseQuantity++
                                },
                                minus: function () {
                                    this.purchaseQuantity--
                                },
                            },
                        })
                    </script>
                    `,
    })
}
let shopCartAddSubmit = function (form) {
    let formData = new FormData(form)
    $.ajax({
        url: form.action,
        type: "POST",
        data: formData,
        contentType: false,
        cache: false,
        processData: false,
        success: function (result) {
            if (result.result) {
                layer.alert('购物车添加成功', {title: '购物车添加成功', icon: 6})
                if (layerTipShopCartAdd) {
                    layer.close(layerTipShopCartAdd)
                }
            }
        }
    })
    return false
}
