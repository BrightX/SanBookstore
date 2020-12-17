let applyShopLayer
let manageShopUrl
let doApplyShop = function (applyShopUrl, shopUrl) {
    manageShopUrl = shopUrl
    applyShopLayer = layer.open({
        type: 1,
        shadeClose: false,
        title: false,
        shade: 0.25,
        area: ['400px', '300px'],
        content: `<form action="${applyShopUrl}" method="post" onsubmit="applyShopSubmit(this); return false;"
              id="apply-shop-form" style="padding: 10px">
            <div class="alert alert-warning text-center h5">申请商铺</div>
            
            <div class="form-group">
                <div class="input-group input-group-lg">
                    <div class="input-group-prepend">
                        <label class="input-group-text" for="id-name"><i class="fa fa-lg fa-pencil-square-o"></i></label>
                    </div>
                    <input type="hidden" name="next" value="${location.href}">
                    <input type="text" class="form-control" required maxlength="30" minlength="2" name="name" id="id-name" autocomplete="off"
                           placeholder="请输入商铺名称">
                </div>
            </div>
            
            <div class="form-group">
                <div class="input-group input-group-lg">
                    <div class="input-group-prepend">
                        <label class="input-group-text" for="id-sendAddress"><i class="fa fa-lg fa-paper-plane-o"></i></label>
                    </div>
                    <textarea class="form-control" required name="sendAddress" id="id-sendAddress" autocomplete="off"
                           placeholder="请输入发货地址"></textarea>
                </div>
            </div>
            
            <div class="form-group text-center">
                <input type="submit" class="btn btn-primary" value="提交申请">
                &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="reset" class="btn btn-outline-secondary" value="重 置">
            </div>
        </form>`
    })
}

let applyShopSubmit = function (form) {
    let formData = new FormData(form)
    let name = formData.get("name")
    let sendAddress = formData.get("sendAddress")
    $.ajax({
        url: form.action,
        type: "POST",
        data: {
            next: formData.get("next"),
            name,
            sendAddress,
        },
        beforeSend: function () {
            layer.msg("正在申请商铺，请稍等。。。")
        },
        success: function (result) {
            // console.log(result)
            if (result.id > 0) {
                layer.msg("商铺申请成功")
                layer.close(applyShopLayer)
                location.href = manageShopUrl
            } else {
                layer.msg("商铺申请失败")
            }
        },
        error: function () {
            layer.msg("400 商铺申请失败")
        },
    })
    return false;
}
