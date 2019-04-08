$(document).ready(function () {

    $("#saveTopic").click(function () {
        var topic_visibility=$('#create_topic_visibility').find(":selected").text();
        var topic_name=$("#create_topic_name").val();

        if (topic_name==""){

            swal("Forgot topic name?", "Cannot save topic without topic name", "error", {
                button: "Try again!",
            });

        } else{
            $.ajax({
                method: "POST",
                url: "/createTopic",
                data: { topicName: topic_name, visibility: topic_visibility }
            })
                .done(function( msg ) {
                    $("#create_topic_name").val("");
                    $('#commentModel').modal('hide')
                    $.notify({
                        // options
                        message: 'topic saved successfully'
                    },{
                        // settings
                        type: 'success'
                    });
                }).fail(function () {

                $.notify({
                    // options
                    message: 'unable to save topic'
                },{
                    // settings
                    type: 'danger'
                });
            });
        }

    });


    $("#addLinkResource").click(function(){alert("askhaskd")});
});