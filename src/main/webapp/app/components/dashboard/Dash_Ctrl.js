(function() {
	var Dash_Ctrl = function($scope, $state, $rootScope, Core_Service,$mdDialog,$mdMedia) {
		var vm = this;
		$rootScope.active = 'dashboard';


        $scope.colors = ["#fc0003", "#f70008", "#f2000d", "#ed0012", "#e80017", "#e3001c", "#de0021", "#d90026", "#d4002b", "#cf0030", "#c90036", "#c4003b", "#bf0040", "#ba0045", "#b5004a", "#b0004f", "#ab0054", "#a60059", "#a1005e", "#9c0063", "#960069", "#91006e", "#8c0073", "#870078", "#82007d", "#7d0082", "#780087", "#73008c", "#6e0091", "#690096", "#63009c", "#5e00a1", "#5900a6", "#5400ab", "#4f00b0", "#4a00b5", "#4500ba", "#4000bf", "#3b00c4", "#3600c9", "#3000cf", "#2b00d4", "#2600d9", "#2100de", "#1c00e3", "#1700e8", "#1200ed", "#0d00f2", "#0800f7", "#0300fc"];

        function getSlide(target, style) {
            var i = target.length;
            return {
                id: (i + 1),
                label: 'slide #' + (i + 1),
                img: 'http://lorempixel.com/450/300/' + style + '/' + ((i + 1) % 10) ,
                color: $scope.colors[ (i*10) % $scope.colors.length],
                odd: (i % 2 === 0)
            };
        }

        function addSlide(target, style) {
            target.push(getSlide(target, style));
        };

        $scope.carouselIndex = 3;
        $scope.carouselIndex2 = 0;
        $scope.carouselIndex2 = 1;
        $scope.carouselIndex3 = 5;
        $scope.carouselIndex4 = 5;

        function addSlides(target, style, qty) {
            for (var i=0; i < qty; i++) {
                addSlide(target, style);
            }
        }

        // 1st ngRepeat demo
        $scope.slides = [];
        addSlides($scope.slides, 'sports', 50);

        // 2nd ngRepeat demo
        $scope.slides2 = [];
        addSlides($scope.slides2, 'sports', 10);

        // 3rd ngRepeat demo
        $scope.slides3 = [];
        addSlides($scope.slides3, 'people', 50);

        // 4th ngRepeat demo
        $scope.slides4 = [];
        addSlides($scope.slides4, 'city', 50);


        // 5th ngRepeat demo
        $scope.slides6 = [];
        $scope.carouselIndex6 = 0;
        addSlides($scope.slides6, 'sports', 10);
        $scope.addSlide = function(at) {
            if(at==='head') {
                $scope.slides6.unshift(getSlide($scope.slides6, 'people'));
            } else {
                $scope.slides6.push(getSlide($scope.slides6, 'people'));
            }
        }
        
        // End to End swiping
        // load 130 images in main javascript container
        var slideImages = [];
        addSlides(slideImages, 'sports', 10);
        addSlides(slideImages, 'people', 10);
        addSlides(slideImages, 'city', 10);
        addSlides(slideImages, 'abstract', 10);
        addSlides(slideImages, 'nature', 10);
        addSlides(slideImages, 'food', 10);
        addSlides(slideImages, 'transport', 10);
        addSlides(slideImages, 'animals', 10);
        addSlides(slideImages, 'business', 10);
        addSlides(slideImages, 'nightlife', 10);
        addSlides(slideImages, 'cats', 10);
        addSlides(slideImages, 'fashion', 10);
        addSlides(slideImages, 'technics', 10);
        $scope.totalimg = slideImages.length;
        $scope.galleryNumber = 1;
        console.log($scope.galleryNumber);
        
        function getImage(target) {
            var i = target.length
                , p = (($scope.galleryNumber-1)*$scope.setOfImagesToShow)+i;
            console.log("i=" + i + "--" + p);
            
            return slideImages[p];
        }
        function addImages(target, qty) {
                            
            for (var i=0; i < qty; i++) {
                addImage(target);
            }
        }
        
        function addImage(target) {
            target.push(getImage(target));
        }
        
        $scope.slides7 = [];
        $scope.carouselIndex7 = 0;
        $scope.setOfImagesToShow = 3;
        addImages($scope.slides7, $scope.setOfImagesToShow);
        $scope.loadNextImages = function() {
            console.log("loading Next images");
            if (slideImages[slideImages.length-1].id !== $scope.slides7[$scope.slides7.length-1].id) {
                // Go to next set of images if exist
                $scope.slides7 = [];
                $scope.carouselIndex7 = 0;
                ++$scope.galleryNumber;
                addImages($scope.slides7, $scope.setOfImagesToShow);
            } else {
                // Go to first set of images if not exist
                $scope.galleryNumber = 1;
                $scope.slides7 = [];
                $scope.carouselIndex7 = 0;
                addImages($scope.slides7, $scope.setOfImagesToShow);
            }
        }
            $scope.items = [];
            
            vm.itemsss= function () {
              for (var i = 1; i <= 1000; i++) {
                $scope.items.push("News "+i);
              }
            
          }
          $scope.showPrompt = function(ev) {
                // Appending dialog to document.body to cover sidenav in docs app
                var confirm = $mdDialog.prompt()
                  .title('What would you like to broadcast')
                  .textContent('Your broadcast goes here')
                  .placeholder('Message')
                  .ariaLabel('Broadcast')
                  .targetEvent(ev)
                  .ok('Send')
                  .cancel('Cancel');
                $mdDialog.show(confirm).then(function(result) {
                  $scope.status = 'You decided to broadcase ' + result + '.';
                }, function() {
                  $scope.status = 'You canceled';
                });
              };
       $scope.loadPreviousImages = function() {
            if (slideImages[0].id !== $scope.slides7[0].id) {
                // Go to previous set of images if exist
                $scope.slides7 = [];
                $scope.carouselIndex7 = 0;
                --$scope.galleryNumber;
                addImages($scope.slides7, $scope.setOfImagesToShow);
            } else {
                // Go to last set of images if not exist
                console.log("slideimageslength: " + slideImages.length + ", " + slideImages.length-1 / $scope.setOfImagesToShow);
                // console.log("slideimageslength: " + slideImages.length );
                $scope.galleryNumber = slideImages.length / $scope.setOfImagesToShow;
                $scope.slides7 = [];
                $scope.carouselIndex7 = 0;
                addImages($scope.slides7, $scope.setOfImagesToShow);
                console.log("no images left");
            }
            
        }

		vm.data = [ {
			key : "One",
			y : 5
		}, {
			key : "Two",
			y : 2
		}, {
			key : "Three",
			y : 9
		}, {
			key : "Four",
			y : 7
		}, {
			key : "Five",
			y : 4
		}, {
			key : "Six",
			y : 3
		}, {
			key : "Seven",
			y : 9
		} ];
		vm.options = {
			"chart" : {
				"type" : "pieChart",
				"height" : 450,
				"donut" : true,
				"showLabels" : true,
				"pie" : {},
				"x" : function(d) {
					return d.key;
				},
				"y" : function(d) {
					return d.y;
				},
				"duration" : 500,
				"legend" : {
					"margin" : {
						"top" : 5,
						"right" : 140,
						"bottom" : 5,
						"left" : 0
					}
				}
			}
		}
		$('#demo').waterbubble({

			  // bubble size
			  radius: 100,

			  // border width
			  lineWidth: undefined,

			  // data to present
			  data: 0.5,

			  // color of the water bubble
			  waterColor: 'rgba(25, 139, 201, 1)',

			  // text color
			  textColor: 'rgba(06, 85, 128, 0.8)',

			  // custom font family
			  font: '',

			  // show wave
			  wave: true,

			  // custom text displayed inside the water bubble
			  txt:"50%",

			  // enable water fill animation
			  animation: true
			  
			});

	};

	Dash_Ctrl.$inject = [ "$scope", '$state', '$rootScope', 'Core_Service' ,'$mdDialog','$mdMedia'];
	angular.module('coreModule').controller('Dash_Ctrl', Dash_Ctrl);
})();
