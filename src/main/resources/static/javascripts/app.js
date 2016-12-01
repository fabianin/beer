var appBeer = angular.module("appBeer",[]);

appBeer.controller("pesquisaController",function($scope,$http){	
	$scope.orderByMe = function(x){
		$scope.myOrderBy = x;
	}
	
	$scope.beerPesquisa;
	$scope.pesquisar = function(){
		$http({
			method: "GET",
			url: '/beer/pesquisa',
			params: $scope.beerPesquisa,
		}).then(
				function(response){
					$scope.beerLista = response.data;
					console.log(response.data);
				},
				function(response){
					console.log("+++++++++");
					console.log(response.status);
					});
	}
	$scope.pesquisar();
	
	$scope.configModal = function(beer){
		$scope.beerRemover = beer;
	}
	
	$scope.remover = function(){
		var token = $("input[name='_csrf']").val();
		var header = $("input[name='_csrf_header']").val();
		var headers = {};
		headers[header] = token;
		$http({
			method: "DELETE",
			url: '/beer/deletar',
			headers: headers,
			params: $scope.beerRemover,
		}).then(
				function(response){
					$scope.beerLista = response.data;
					console.log(response.data);
					$scope.pesquisar();
				},
				function(response){
					console.log("+++++++++");
					console.log(response.status);
					});
	}
	
	
});
