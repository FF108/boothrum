# Boothrum

Boothrum est une application permettant de consulter des images sur les booru (Image Boards). L'application permettra de télécharger et mettre en favoris des images ainsi qu'ajouter ses propres booru parmi la liste des boards.


-- Rendu du projet Android AP5 ISEN --

L'implémentation de retrofit et de Ktor ayant posé problème pendant un certain temps, les appels aux API booru sont fait via OkHttp. L'appel API ayant été un problème bloquant, le retard induit fait que certaines fonctionnalités tel que la recherche par tag, les favoris et le changement de board n'est actuellement pas fonctionnel. Des options telles que la sauvegarde de l'image peuvent également manquer à l'appel.

Enfin, pour ces mêmes raisons, la réalisation de l'application a dû être réalisée à la hâte afin de permettre une démo satisfaisante pour le rendu de l'unité d'enseignement. De ce fait, nous nous excusons par avance pour la structure plus que douteuse du code à certains endroits.

## Contributors
- Michel Carrion
- Killian Houche
- Raphaël Gosset

## License

[MIT](https://choosealicense.com/licenses/mit/)
