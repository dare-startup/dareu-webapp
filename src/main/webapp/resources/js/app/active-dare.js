$(function () {
    //ko.applyBindings(new ActiveDareViewModel());
    //get timer value 
    var timer = $("#timer").val(); 
    if(timer){
        checkTimerValue(timer);
    }else{
        setInterval(setTimerValue, 1500); 
    }
});


function checkTimerValue(){
    var timer = $("#timer").val(); 
    //new date 
    var now = new Date(); 
    var acceptedDate = $("#acceptedDate").text(); 
    var before = new Date(acceptedDate);
    
    var timerTotalMs = timer * 60 * 60 * 1000; 
    if((now.getTime() - before.getTime()) < timerTotalMs){
        //there is time to complete challenge
        var distance = now.getTime() - before.getTime(); 
        //get hours from ms 
        var hours = parseInt((distance/(1000*60*60))%24);
        var minutes = minutes = parseInt((distance/(1000*60))%60); 
        var seconds = seconds = parseInt((distance/1000)%60);
        var format = hours + " hours, " + minutes + " minutes, " + seconds + " seconds"; 
        $("#timerMessage").text("Time left to expire: " + format);
    }else{
        //expired 
        $("#timerMessage").text("This dare is expired");
    }
}

function ActiveDareViewModel() {
    var self = this;
    self.timer = ko.pureComputed({
        read: function () {
            return "00days, 00hours 00minutes 00seconds";
        }, 
        write: function(value){
            //get estimated dare time 
            var timer = $('#timer').val();
            //get accepted date
            var acceptedDate = $('acceptedDate').val();
            //show the timer
            showTimer(timer, acceptedDate, self);
        }
    });
    self.timer();
}

function showTimer(timer, date, self) {
    var end = new Date(date);
    //add timer 
    end.setTime(end.getTime() + (timer * 60 * 60 * 1000));

    var sec = 1000;
    var min = sec * 60;
    var hour = min * 60;
    var day = hour * 24;
    var timer;
    function showRemaining() {
        var now = new Date();
        var distance = end - now;
        if (distance < 0) {
            //expired
            clearInterval(timer);
            self.timer("Expired");
            return;
        }

        var days = Math.floor(distance / day);
        var hours = Math.floor((distance % day) / hour);
        var minutes = Math.floor((distance % hour) / min);
        var seconds = math.floor((distance % min) / sec);

        var text = days + " days, " + hours + " hours, " + minutes + " minutes, " + seconds + " seconds";
        self.timer(text);
    }
    //set interval
    timer = setInterval(showRemaining, 1000);
}