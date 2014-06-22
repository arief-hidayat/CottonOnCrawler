
<html>
<head>
    <meta name="layout" content="hida"/>
</head>
<body>
    <div class="row">
        <g:each in="${products}" var="item">
            <div class="col-xs-12 col-sm-6 col-md-4">
                <div class="row">
                    <div class="col-xs-6 col-md-6">
                        <img src="${item.imgUrl}" alt="${item.title}"  class="img-thumbnail">
                    </div>
                    <div class="col-xs-6 col-md-6" style="margin-top:15px">
                        <p></p>
                        <p><h4><strong><a href="${item.productUrl}">${item.title}.</a></strong></h4></p>
                        <p><strong><g:formatNumber number="${item.suggestedIdrPrice}" type="currency" currencyCode="IDR"/></strong></p>
                        <p style="display: none;">was S$${item.priceWas}, now S$${item.priceNow}</p>
                        <p><strong>${item.salePercentage}% OFF</strong></p>
                    </div>
                </div>
            </div>
        </g:each>
    </div>
</body>
</html>