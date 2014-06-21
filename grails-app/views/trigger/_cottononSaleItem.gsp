<li id="${item.id}" style="list-style: none;">
    <img src="${item.imgUrl}" alt="${item.title}" style="width: 100px;">
    <div>
        <strong><a href="${item.productUrl}">${item.title}.</a></strong>
        <br>
        <strong>was S$${item.priceWas}, now S$${item.priceNow}. discount ${item.salePercentage}%</strong>
        <br>
        <strong>suggested price IDR${item.suggestedIdrPrice}</strong>
    </div>

</li>