$(document).ready(function () {
            //1. hide error sections
            $("#uomTypeError").hide();
            $("#uomModelError").hide();
            $("#descriptionError").hide();

            //2. define error variables
            var uomTypeError = false;
            var uomModelError = false;
            var descriptionError = false;

            //3. validate function
            function validate_uomType() {
                //a. read the input value
                var val = $("#uomType").val();
                //b. check condition
                if (val == '') {
                    $("#uomTypeError").show();
                    $("#uomTypeError").html("SELECT <b>UOM TYPE</b>");
                    $("#uomTypeError").css("color", "red");
                    uomTypeError = false;
                } else {
                    $("#uomTypeError").hide();
                    uomTypeError = true;
                }
                return uomTypeError;
            }

            function validate_uomModel() {
                //a. read the input value
                var val = $("#uomModel").val();
                //regex
                var exp = /^[A-Z\-\s]{4,8}$/;

                //b. check condition
                if (val == '') {
                    $("#uomModelError").show();
                    $("#uomModelError").html("Model <b>cannot be empty</b>");
                    $("#uomModelError").css("color", "red");
                    uomModelError = false;
                } else if (!exp.test(val)) {
                    $("#uomModelError").show();
                    $("#uomModelError").html("Model <b>is not valid</b>");
                    $("#uomModelError").css("color", "red");
                    uomModelError = false;
                } else {
                	//AJAX CALL start
                	var id = 0;
                	if($("#id").val() !== undefined){  //for edit page
                		id = $("#id").val();
                	}
                	$.ajax({
                		url : 'validate',
                		data: {'model':val, 'id':id},
                		success:function(resTxt){
                			if(resTxt==''){
                                $("#uomModelError").hide();
                                uomModelError = true;
                			}else{
                				$("#uomModelError").show();
                                $("#uomModelError").html(resTxt);
                                $("#uomModelError").css("color", "red");
                                uomModelError = false;
                				
                			}
                		}
                	});  //AJAX CALL ends                 	               	
                }
                return uomModelError;
            }

            function validate_description() {
                //a. read the input value
                var val = $("#description").val();
                //regex
                var exp = /^[A-Za-z0-9\-\.\,\s]{10,100}$/;

                //b. check condition
                if (val == '') {
                    $("#descriptionError").show();
                    $("#descriptionError").html("Description <b>cannot be empty</b>");
                    $("#descriptionError").css("color", "red");

                    descriptionError = false;

                } else if (!exp.test(val)) {
                    $("#descriptionError").show();
                    $("#descriptionError").html("Description <b>must be 10-100 chars only</b>");
                    $("#descriptionError").css("color", "red");

                    descriptionError = false;

                } else {
                    $("#descriptionError").hide();

                    descriptionError = true;
                }

                return descriptionError;
            }

            //4. link input with actions events
            $("#uomType").change(function () {
                validate_uomType();
            });

            $("#uomModel").keyup(function () {
                validate_uomModel();
            });

            $("#description").keyup(function () {
                validate_description();
            });


            //5. submit button
            $("#uomRegForm").submit(function () {
                //call all validate functions
                validate_uomType();
                validate_uomModel();
                validate_description();

                //check all error variables
                //if all true then return true and submit form, else false
                if (uomTypeError && uomModelError && descriptionError) {
                    return true;
                } else {
                    return false;
                }
            });
        });