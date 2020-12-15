let layTipToBuy
let buyBookForm
let toBuy = function (book, shop) {
    layTipToBuy = layer.open({
        type: 1,
        shadeClose: false,
        title: false,
        shade: 0.25,
        area: ['800px', '600px'],
        content: `<form action="${buyBookUrl}" method="post" onsubmit="return false;"
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
            <!-- todo 用户买书 表单 -->
            <input type="hidden" name="purchaseQuantity" v-bind:value="purchaseQuantity">
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
                        this.paymentAmount = this.purchaseQuantity * book.price
                    },
                },
                methods: {
                    add: function () {
                        this.purchaseQuantity++
                    },
                    minus: function () {
                        if (this.purchaseQuantity > 1) {
                            this.purchaseQuantity--
                        }
                    },
                },
            })
        </script>
        `,

    })
}