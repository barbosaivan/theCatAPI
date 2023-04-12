# theCatAPI

_Aplicación desarrollada en kotlin usando la arquitectura MVVC, que presenta información obtenida mediante un consumo API REST._

_Se implementó la librería Volley en su versión 1.2.1 para realizar el consumo._

_La información se presenta en contenedores de tipo CardView, los cuales fueron inflados mediante un adaptador._

_Los datos presentados son, raza, pais origen, inteligencia e imagen._


## Instrucciones de instalacion

_El proyecto se encuentra en la rama master._

_La clonacion del proyecto se realiza mediente la siguiente Url: https://github.com/barbosaivan/theCatAPI.git_

_El nivel de API minimo requerido para la instalacion del la aplicacion es: 24_

_El android gradle plugin version usado es: 7.4.0_

_La aplicacion requiere permisos de internet_

## Funcionalidad
_La aplicación muestra la información requerida de los gatos en contenedores tipo cardView"_

_La aplicación cuenta con una interfaz escroleable que muestra un listado de gatos_

## Url API REST
_https://api.thecatapi.com/v1/breeds?x-api-key=bda53789-d59e-46cd-9bc4-2936630fde39s_

## Formato de la informacion recibida
_[
  {
    "weight": {
      "imperial": "7  -  10",
      "metric": "3 - 5"
    },
    "id": "abys",
    "name": "Abyssinian",
    "cfa_url": "http://cfa.org/Breeds/BreedsAB/Abyssinian.aspx",
    "vetstreet_url": "http://www.vetstreet.com/cats/abyssinian",
    "vcahospitals_url": "https://vcahospitals.com/know-your-pet/cat-breeds/abyssinian",
    "temperament": "Active, Energetic, Independent, Intelligent, Gentle",
    "origin": "Egypt",
    "country_codes": "EG",
    "country_code": "EG",
    "description": "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
    "life_span": "14 - 15",
    "indoor": 0,
    "lap": 1,
    "alt_names": "",
    "adaptability": 5,
    "affection_level": 5,
    "child_friendly": 3,
    "dog_friendly": 4,
    "energy_level": 5,
    "grooming": 1,
    "health_issues": 2,
    "intelligence": 5,
    "shedding_level": 2,
    "social_needs": 5,
    "stranger_friendly": 5,
    "vocalisation": 1,
    "experimental": 0,
    "hairless": 0,
    "natural": 1,
    "rare": 0,
    "rex": 0,
    "suppressed_tail": 0,
    "short_legs": 0,
    "wikipedia_url": "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
    "hypoallergenic": 0,
    "reference_image_id": "0XYvRd7oD"
  }...
  ]_
 
 ## Herramientas
 * androidx
 
 * google material
 
 * volley
 
 * Glide
 
 ## Dependencias implementasdas
 
* ViewModel

 _implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1"_

* Dependecia andoroid ktx

_implementation("androidx.activity:activity-ktx:1.6.1")_

* volley

_implementation 'com.android.volley:volley:1.2.1'_

* Gluide

_implementation 'com.github.bumptech.glide:glide:4.15.1'_
 
 ## Autor
 _Ivan Barbosa Ortega_
_Practica correspondiente a la prueba practica para la vacante desarrollador android en PRAGMA_
 
 _Estudiante de Ingenieria de sistemas_


