document.addEventListener("DOMContentLoaded", function () {

  var totalText = document.getElementById("pointsTotalText");

  // how many points is MAX by default
  var maxPoints = 1000;

  function showPoints(value) {
    // make sure it's a number
    if (typeof value === "string") {
      value = parseInt(value, 10);
    }
    if (isNaN(value)) {
      value = 0;
    }

    // if backend sends more than max, stretch max
    if (value > maxPoints) {
      maxPoints = value;
    }

    // if reached max or more rewrite the number with MAX
    if (value >= maxPoints) {
      totalText.textContent = "MAX";
      totalText.classList.add("points-total-max");
    } else {
      totalText.textContent = "Current points: " + value + " / " + maxPoints;
      totalText.classList.remove("points-total-max");
    }
  }

  showPoints(0);
  fetch("/api/greenPoints")
    .then(function (response) {
      return response.json();
    })
    .then(function (data) {
      console.log("Green points from server:", data);
      showPoints(data.totalPoints);
    })
    .catch(function (error) {
      console.log("Error fetching green points:", error);
    });

});