document.addEventListener("DOMContentLoaded", function () {

    var grid = document.getElementById("tradeGrid");
    if (!grid) {
        console.log("tradeGrid not found");
        return;
    }

    fetch("/api/pointTrades")
        .then(function (response) {
            return response.json();
        })
        .then(function (list) {
            console.log("Loaded trades:", list);

            grid.innerHTML = "";

            for (var i = 0; i < list.length; i++) {
                var trade = list[i];

                var card = document.createElement("div");
                card.className = "trade-card green";

                var indicator = document.createElement("div");
                indicator.className = "trade-indicator vertical";

                var s1 = document.createElement("span");
                s1.className = "c1";
                indicator.appendChild(s1);

                var s2 = document.createElement("span");
                s2.className = "c2";
                indicator.appendChild(s2);

                var s3 = document.createElement("span");
                s3.className = "c3";
                indicator.appendChild(s3);

                var s4 = document.createElement("span");
                s4.className = "c4";
                indicator.appendChild(s4);

                var title = document.createElement("h3");
                title.className = "trade-title";
                title.textContent = trade.tradeName;

                var desc = document.createElement("p");
                desc.className = "trade-description";
                desc.textContent = trade.description;

                var meta = document.createElement("p");
                meta.className = "trade-meta";
                meta.textContent = trade.pointAmount + " pts";

                card.appendChild(indicator);
                card.appendChild(title);
                card.appendChild(desc);
                card.appendChild(meta);

                grid.appendChild(card);
            }
        })
        .catch(function (err) {
            console.log("Error fetching trades:", err);
        });

});