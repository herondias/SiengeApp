(function() {
  'use strict';
  
var app = angular.module('SiengeApp', ['ngAnimate', 'toastr', 'ngRoute'])
app.controller('TransportadoraController', TransportadoraController);

function TransportadoraController($http, toastr) {
	var vm = this
	vm.tiposVeiculos = [];
	vm.tipoVeiculo = {};
	vm.simulacaoParam = {};
	vm.simulacao = {};
	getTiposVeiculos();
	
	function getTiposVeiculos() {
		$http({
			  method: 'GET',
			  url: '/listarTiposVeiculos'
		}).then(function (response) {
			vm.tiposVeiculos = response.data;
		});
	}
	
	vm.simularCustos = function(simulacaoParam){
		if(!validarParametrosSimulacao(simulacaoParam)){
			return false;
		} else {
			$http({
				  method: 'POST',
				  url: '/simularCustoTransporte',
				  data: simulacaoParam
			}).then(function (response) {
				vm.simulacao = response.data;
			}, function (error) {
				vm.simulacao = {};
				console.log(error.data);
				toastr.error(error.data.message, 'Erro!');
			});
		}
	}
	
	vm.salvarTipoVeiculo = function(tipoVeiculo){
		if(!validarTipoVeiculo(tipoVeiculo)){
			return false;
		} else {
			$http({
				  method: 'POST',
				  url: '/addTipoVeiculo',
				  data: tipoVeiculo
			}).then(function (response) {
				vm.transporte = response.data;
				vm.tipoVeiculo = {};
				toastr.success('Tipo de veículo cadastrado com sucesso!');
			}, function (error) {
				toastr.error(error.data.message, 'Erro!');
			});
		}
	}
	
	/**
	 * Limpa formulário de dados de simulação de custo
	 */
	vm.limpar = function(){
		vm.simulacaoParam = {};
		vm.simulacao = {};
	}
	
	/**
	 * Valida paraqmetros de entrada vindo do form
	 */
	function validarParametrosSimulacao(simulacaoParam) {
		var erros = [];
		
		if(typeof simulacaoParam.fatorMultiplicador == 'undefined' || simulacaoParam.fatorMultiplicador === null){
			erros.push('Selecione o tipo de Veículo para simular custo do frete');
		}
		
		if(typeof simulacaoParam.pesoCarga == 'undefined' || simulacaoParam.pesoCarga === null){
			erros.push('Entre com valor correspondente ao peso da carga');
		} else if(!Number.isInteger(simulacaoParam.pesoCarga)){
			erros.push('O valor da carga deve ser inteiro');
		}
		
		if(erros.length > 0) {
			for(var i = 0; i < erros.length; i++){
				toastr.error(erros[i], 'Erro!');
			}
			return false;
		}
		return true;
	}
	
	function validarTipoVeiculo(tipoVeiculo) {
		var erros = [];
		
		if(typeof tipoVeiculo.fatorMultiplicador == 'undefined' || tipoVeiculo.fatorMultiplicador === null){
			erros.push('Digite o valor do fator muplicador para o tipo de veículo');
		}
		
		if(typeof tipoVeiculo.descricao == 'undefined' || tipoVeiculo.descricao === null){
			erros.push('Digite o nome tipo de veiculo deseja cadastrar');
		}
		
		if(erros.length > 0){
			for(var i = 0; i < erros.length; i++){
				toastr.error(erros[i]);
			}
			return false;
		}
		return true;
	}
}

app.config(routeConfig);
function routeConfig($routeProvider, $locationProvider) { 
	
	$routeProvider.when('/simularCalculoFrete', {
      templateUrl: 'views/simularCalculoFrete.html',
      controller: 'TransportadoraController',
      controllerAs: 'transp'
    })
    .when('/cadastrarVeiculo', {
      templateUrl: 'views/cadastrarTipoVeiculo.html',
      controller: 'TransportadoraController',
      controllerAs: 'transp'
    }).when('/sobre', {
        templateUrl: 'views/sobre.html',
        controller: 'TransportadoraController',
        controllerAs: 'transp'
      }).otherwise({
      redirectTo: '/simularCalculoFrete'
    });
}

})();
