document.addEventListener("DOMContentLoaded", function () {

    var grid = document.getElementById("tasksGrid");
    if (!grid) {
        console.log("tasksGrid not found");
        return;
    }

    fetch("/api/greenActions")
        .then(function (response) {
            return response.json();
        })
        .then(function (list) {
            console.log("Loaded community tasks:", list);
            grid.innerHTML = "";

            for (var i = 0; i < list.length; i++) {
                var task = list[i];

                var card = document.createElement("div");
                card.className = "task-card";

                var indicator = document.createElement("div");
                indicator.className = "task-indicator";

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
                title.className = "task-title";
                title.textContent = "Green task";

                var desc = document.createElement("p");
                desc.className = "task-description";
                desc.textContent = task.description;

                var meta = document.createElement("p");
                meta.className = "task-meta";
                meta.textContent = "Reward: " + task.pointValue + " pts";

                card.appendChild(indicator);
                card.appendChild(title);
                card.appendChild(desc);
                card.appendChild(meta);

                grid.appendChild(card);
            }
        })
        .catch(function (err) {
            console.log("Error fetching community tasks:", err);
        });
});