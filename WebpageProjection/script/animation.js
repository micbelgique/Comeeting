$(document).ready(function () {
    // var randomFacts = [
    //     {
    //         pictureClass: "valentin",
    //         title: "Valentin Taleb",
    //         subtitle: "Senior Android Developer",
    //         text: "a travaillé chez <strong class='job-name'>Agilys</strong>"
    //     },
    //     {
    //         pictureClass: "laurent",
    //         title: "Laurent Vandenbosch",
    //         subtitle: ".NET Developer",
    //         text: "a travaillé chez <strong class='job-name'>Partenamut</strong>"
    //     },
    //     {
    //         pictureClass : mathias,
    //         title: "Mathias Biard",
    //         subtitle: "iOS Developer",
    //         text: "a travaillé chez <strong class='job-name'>Ta mère SPRL</strong>"
    //     },
    //     {
    //         pictureClass:"francois",
    //         title: "François Stefany",
    //         subtitle: "iOS/Android Developer",
    //         text: "a travaillé chez <strong class='job-name'>Ta mère SPRL</strong>"
    //     },
    //     {
    //         pictureClass : "thomas",
    //         title: "Thomas Fekenne",
    //         subtitle: "",
    //         text: "est arrivé au coworkspace."
    //     }
    // ]

    // startSlider();

     $('.slider').slick({
         arrows:false,
         autoplay:true,
         autoplaySpeed:5000,
         variableWidth:false
     });

    
    //sliding.toggle("slide");

});

function startSlider(){
    setTimeout(() => {nextSlide()},5000);
}
function nextSlide(){
    var sliding = $(".content-slider");

    setTimeout(function() {
        nextSlide();
    }, 5000);
}