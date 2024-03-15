$(document).ready(function() {
    $('a[id^="supprimer-"]').click(function(e) {
        e.preventDefault();
        if (confirm("Voulez-vous vraiment supprimer ce joueur ?")) {
            var url = $(this).attr("href");
            $.post(url, function() {
                location.reload();
            });
        }
    });
});