$(document).ready(function () {


    /*Todo: public topics should be unique check*/
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
                    $('#commentModel').modal('hide');
                    $.notify({
                        message: topic_name+' topic saved successfully'
                    },{ offset:60,
                        type: 'success'
                    });
                    setTimeout(function(){ location.reload() }, 2000);
                }).fail(function () {

                $.notify({
                    message: 'unable to save topic'
                },{ offset:60,
                    type: 'danger'
                });
            });
        }

    });


    $("#addLinkResource").click(function(){

        var linkTitle=$("#link_resource_name").val();
        var linkDescription=$("#link_resource_description").val();
        var linkTopic=$("#link_resource_topic").val();
        var linkLocation=$("#link_resource_location").val();
        var selectedTopic=$('#link_resource_topic').find(":selected").text();
        if (linkTitle != "" &&
            linkDescription!="" &&
            linkLocation!="" &&
            linkTopic!=""){


            //check if url is valid or not
            var urlregex = new RegExp(
                "^(http:\/\/www.|https:\/\/www.|ftp:\/\/www.|www.){1}([0-9A-Za-z]+\.)");

            var validUrl =urlregex.test(linkLocation);
            if (validUrl==true){
                $.ajax({
                    method: "POST",
                    url:"/createLinkResource",
                    data: {postName:linkTitle,
                        postDescription:linkDescription,
                        location:linkLocation,
                        type:"LINK",
                        topic:linkTopic}

                }).done(function(data){
                    $("#link_resource_name").val("");
                    $("#link_resource_description").val("");
                    $("#link_resource_location").val();
                    $('#linkResourceModel').modal('hide');

                    $.notify({
                        message: 'successfully added link resource to topic '+selectedTopic
                    },{
                        offset:60,
                        type: 'success'
                    });
                    setTimeout(function(){ location.reload() }, 2000);

                }).fail(function(data){
                    swal("Error saving link resource", "Please try again", "error", {
                        button: "okay",
                    });
                });
            } else{
                swal("But the link was not valid", "Please enter a valid link", "error", {
                    button: "Try again!",
                });
            }

        } else{
            swal("Did You Left Some fields?", "All fields are required to create link resource", "error", {
                button: "Try again!",
            });
        }

    });

    $("#documentResourceForm").submit(function(e){
        e.preventDefault();
        var documentTitle=$("#document_resource_name").val();
        var documentDescription=$("#document_resource_description").val();
        var documentTopic=$("#document_resource_topic").val();
        var documentFile=$("#document_resource_location").val();
        var selectedTopic=$('#document_resource_topic').find(":selected").text();
        if (documentTitle != "" &&
            documentDescription!="" &&
            documentFile!="" &&
            documentTopic!=""){

            var formData = new FormData(this);
            formData.append("document_resource_file",$("#document_resource_file")[0].files[0]);
            formData.append("type","DOCUMENT");

            console.log($("#document_resource_file")[0].files[0]);
            $.ajax({
                method: "POST",
                enctype: "multipart/form-data",
                url:"/createDocumentResource",
                data:formData,
                processData: false,
                contentType: false

            }).done(function(data){
                $("#document_resource_name").val("");
                $("#document_resource_description").val("");
                $("#document_resource_location").val("");
                $('#documentResourceModel').modal('hide');

                if (data=="success"){
                    $.notify({
                        message: 'successfully added document resource to topic '+selectedTopic
                    },{ offset:60,
                        type: 'success'
                    });
                    setTimeout(function(){ location.reload() }, 2000);
                }else{
                    swal("Error saving document resource to topic "+selectedTopic, "Please try again", "error", {
                        button: "okay",
                    });
                }

            }).fail(function(data){
                swal("Error saving document resource", "Please try again", "error", {
                    button: "okay",
                });
            });



        } else{
            swal("Did You Left Some fields?", "All fields are required to create link resource", "error", {
                button: "Try again!",
            });
        }

    });


    $(".inboxPost  #markAsRead").click(function () {

        /*
         alert($("#inbox").children().length);

             $(this).parent().parent().parent().parent().parent().fadeOut("normal",function () {
                 $(this).remove();
             });

           alert($("#inbox").children().length);*/

        let postToMarkAsRead= $(this).attr("data-post-id");
        let currentPost=$(this).parent().parent().parent().parent().parent();

        $.ajax({
            method:"post",
            url:"/markPostAsRead",
            data:{postId:postToMarkAsRead},
        }).done(function (data) {
            if (data=="success"){
               $(this).html("asdasdsa");
                currentPost.remove();

                $.notify({
                    message: 'successfully marked post as read'
                },{ offset:60,
                    type: 'success'
                });


                if ($("#inbox").children().length==1){
                    $("#inbox  #emptyInboxMessage").css("display","block");
                }else {
                    $("#inbox  #emptyInboxMessage").css("display","none");

                }
                setTimeout(function(){ location.reload() }, 2000);

            } else{
                $.notify({
                    message: 'unable to mark post as read'
                },{ offset:60,
                    type: 'error'
                });
            }
        }).fail(function(){

            $.notify({
                message: 'unable to mark post as read'
            },{ offset:60,
                type: 'error'
            });

        });


    });

    if ($("#inbox").children().length==1){
        $("#inbox  #emptyInboxMessage").css("display","block");
    }else {
        $("#inbox  #emptyInboxMessage").css("display","none");

    }


    $(".trendingTopic #toggleSubscription").click(function(e){
        e.preventDefault();

        let currentTopic=$(this);
        let topicId=$(this).attr("data-topic-id");
        let toggleOption=$(this).attr("data-subunsub-toggle");

        if (toggleOption=="subscribe"){

            currentTopic.children().html("unsubscribe");
            currentTopic.attr("data-subunsub-toggle","unsubscribe");

            $.ajax({
                method:"POST",
                url:"/subscribeTopic",
                data:{topicId:topicId}
            }).done(function(data){

                if (data=="success"){

                    $.notify({
                        message: 'topic successfully subscribed'
                    },{ offset:60,
                        type: 'success'
                    });
                }else{

                    $.notify({
                        message: 'unexpected error in subscribing topic'
                    },{ offset:60,
                        type: 'error'
                    });
                }

            }).fail(function(){

                $.notify({
                    message: 'internal error in subscribing topic'
                },{ offset:60,
                    type: 'error'
                });
            });


        } else if (toggleOption=="unsubscribe") {
            currentTopic.children().html("subscribe");
            currentTopic.attr("data-subunsub-toggle","subscribe");

            $.ajax({
                method:"POST",
                url:"/unsubscribeTopic",
                data:{topicId:topicId}
            }).done(function(data){

                if (data=="success"){

                    $.notify({
                        message: 'topic successfully unsubscribed'
                    },{ offset:60,
                        type: 'success'
                    });
                }else{

                    $.notify({
                        message: 'unexpected error in subscribing topic '+data
                    },{ offset:60,
                        type: 'error'
                    });
                }

            }).fail(function(data){

                $.notify({
                    message: 'internal error in subscribing topic'+data
                },{ offset:60,
                    type: 'error'
                });
            });

        }else{
            $.notify({
                message: 'wrong toggle option'
            },{ offset:60,
                type: 'error'
            });
        }

    });


    $("#sendInvite").click(function(){
        var topicId=$("#invitation_topic").find(":selected").val();
        var topicName=$("#invitation_topic").find(":selected").text();
        var emailId=$("#receiver_email").val();
        if (emailId==""){
            swal("Did You Left Email blank?", "cannot send invitation without email", "error", {
                button: "Try again!",
            });
        }else{
            $("#sendInvite").attr("disabled", true);
            $.ajax({
                method:"POST",
                url:"/sendTopicInvitation",
                data:{topicId:topicId,email:emailId}
            }).done(function(data){
                if (data=="success"){
                    $('#sendInvitationModel').modal('hide');
                    $("#sendInvite").attr("disabled", false);
                    $.notify({
                        message: 'invitation send successfully for topic '+topicName
                    },{ offset:60,
                        type: 'success'
                    });
                } else{
                    $('#sendInvitationModel').modal('hide');
                    $.notify({
                        message: 'unable to send invitation'
                    },{ offset:60,
                        type: 'error'
                    });
                }
            }).fail(function(){
                $('#sendInvitationModel').modal('hide');
                $.notify({
                    message: 'internal error in sending email'
                },{ offset:60,
                    type: 'error'
                });
            });

        }
    });

    $("#settingLinks #deleteTopic").click(function(e){
        e.preventDefault();
        let deleteTopic=$(this);
        let topicId= deleteTopic.attr("data-topicId");
        let topicName= deleteTopic.attr("data-topicName");

        $.ajax({
            method:"POST",
            url:"/deleteTopic",
            data:{topicId:topicId}
        }).done(function(){
                if (data=="success"){
                    $.notify({
                        message: 'topic '+topicName+' successfully deleted'
                    },{ offset:60,
                        type: 'success'
                    });
                } else{
                    $.notify({
                        message: 'error in deleting topic'
                    },{ offset:60,
                        type: 'error'
                    });
                }
        }).fail(function(){
            $.notify({
                message: 'internal error in deleting topic'
            },{ offset:60,
                type: 'error'
            });
        });

    });

/*
     $("#settingLinks #editTopic").click(function (e) {
         e.preventDefault();
         let editTopic =$(this);
         $("#topicName").removeAttr("readonly")
     });
*/

     $("#dropdownSetting #subscriptionSeriousness").on('change',function (){
        let seriousness =$(this);
        let choosedSeriousnes=$('option:selected',$(this)).text();
        let subscriptionId=seriousness.attr("data-subscriptionId");


        $.ajax({
            method:"POST",
            url:"/changeTopicSeriousness",
            data:{subscriptionId:subscriptionId,choosedSeriousnes:choosedSeriousnes}
        }).done(function(data){

            if (data=="success"){
                $.notify({
                    message: 'seriousness successfully changed to '+choosedSeriousnes
                },{ offset:60,
                    type: "success"
                });
                setTimeout(function(){ location.reload() }, 2000);
            } else{
                $.notify({
                    message: 'error in changing seriouness'
                },{ offset:60,
                    type: "error"
                });
            }

        }).fail(function(){
            $.notify({
                message: 'internal error in changing seriouness'
            },{ offset:60,
                type: "error"
            });
        });
     });

});