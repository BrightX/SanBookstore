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
        title:false,
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
                $("#image-upload").on("change", function () {
                    let file = this.files[0];
                    uploadImg[0].src = URL.createObjectURL(file);
                    uploadImg.show()
                    $("#image-upload-label").text(file.name);
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
