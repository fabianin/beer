var appBeer = angular.module("appBeer",[]);

appBeer.controller("pesquisaController",function($scope,$http){
	$http({
		method: "GET",
		url: '/beer/pesquisa'
	}).then(
			function(response){
				$scope.beerLista = response.data;	
			},
			function(response){
				console.log(response.status);
				});
	
	$scope.orderByMe = function(x){
		$scope.myOrderBy = x;
	}
});
