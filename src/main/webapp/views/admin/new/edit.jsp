<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-new"/>
<c:url var ="NewURL" value="/admin-new-list"/>
<html>
<head>
    <title>Chỉnh sửa bài viết</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">Chỉnh sửa bài viết</li>
            </ul><!-- /.breadcrumb -->
        </div>
        
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                        <c:if test="${not empty message}">
									<div class="alert alert-${alert}">
  										${message}
									</div>
						</c:if>
                        <form id="formSubmit">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Thể loại</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="categoryCode" name="categoryCode">
                                        <c:if test="${empty model.categoryCode}">
                                            <option value="">Chọn loại bài viết</option>
                                            <c:forEach var="item" items="${categories}">
                                                <option value="${item.code}">${item.name}</option>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${not empty model.categoryCode}">
                                            <option value="">Chọn loại bài viết</option>
                                            <c:forEach var="item" items="${categories}">
                                                <option value="${item.code}" <c:if test="${item.code == model.categoryCode}"> selected="selected"</c:if>>
                                                        ${item.name}
                                                </option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Tiêu đề</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="title" name="title" value="${model.title}"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Hình đại diện</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="thumbnail" name="thumbnail" value="${model.thumbnail}"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Mô tả ngắn</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="shortDescription" name="shortDescription" value="${model.shortDescription}"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Nội dung</label>
                                <div class="col-sm-9">                                 
                                    <textarea rows="" cols="" id="content" name="content"  style="width: 820px;height: 175px">${model.content}</textarea>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            
                            
                            <div class="form-group">                    
                                <div class="col-sm-12"> 
                                	<c:if test = "${empty model.id}">
                                    	<input type="button"  class="btn btn-white btn-warning btn-bold" id="btnAddorUpdate"  value="Thêm bài viết"/>
                              		
                                	</c:if>
                                	
                                	<c:if test = "${not empty model.id}">
                                    	<input type="button" class="btn btn-white btn-warning btn-bold" id="btnAddorUpdate"  value="Cập nhật bài viết"/>
                              		
                                	</c:if>                                 
                                </div>
                            </div>
                            
                            <input type="hidden" value="${model.id}" id="id" name="id"/>
                        </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
	$('#btnAddorUpdate').click(function(e){
		e.preventDefault(); // Ngăn chặn hành vi mặc định của sự kiện click đến url mặc định

		var data = {};
		var formData = $('#formSubmit').serializeArray(); // Lấy tất cả các thuộc tính bao gồm (id-value tương ứng) ở trong form.
	  	console.log(formData);
	  	$.each(formData, function (i, v) {
	  		data[""+v.name+""] = v.value;
        });
	  	var id = $('#id').val();
	  	if(id == ""){
	  		addNew(data);
	  	}else{
	  		updateNew(data);
	  	}
	});
	function addNew(data){
		$.ajax({ // ajax là một kỹ thuật để mapping data về dưới dạng json và kết nối với API theo phương thức ResfulAPI để gửi về server xử lí
			url: '${APIurl}', //Là đường dẫn đến cotroller xử lí dữ liệu mà API gửi về cho server
            type: 'POST', // Phương thức gửi về server
            contentType: 'application/json', // Gửi về server dưới dạng json
            data: JSON.stringify(data), // Chuyển đôi file data về dưới dạng json
            dataType: 'json', // Server trả về cho client dưới dạng json
            success: function (result) {
            	console.log(result);
            },
            error: function (error) {
            	console.log(error);
            }
		});
	}
	function updateNew(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	console.log(result);
            },
            error: function (error) {
            	console.log(error);
            }
        });
	}
    
</script>
</body>
</html>
