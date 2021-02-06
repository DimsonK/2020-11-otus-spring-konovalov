(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["main"],{

/***/ "+9od":
/*!******************************************!*\
  !*** ./src/app/services/book.service.ts ***!
  \******************************************/
/*! exports provided: BookService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "BookService", function() { return BookService; });
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! rxjs/operators */ "kU1M");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "tk/3");



class BookService {
    constructor(http) {
        this.http = http;
    }
    loadBooks() {
        return this.http
            .get('/api/book')
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_0__["map"])((data) => data));
    }
    loadBookById(payload) {
        return this.http
            .get(`/api/book/${payload}`)
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_0__["map"])((data) => data));
    }
    createBook(payload) {
        return this.http
            .post('/api/book', payload)
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_0__["map"])((data) => data));
    }
    updateBook(payload) {
        return this.http
            .put(`/api/book/${payload.id}`, payload)
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_0__["map"])((data) => data));
    }
    deleteBook(payload) {
        return this.http
            .delete(`/api/book/${payload}`)
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_0__["map"])((data) => data));
    }
}
BookService.ɵfac = function BookService_Factory(t) { return new (t || BookService)(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵinject"](_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"])); };
BookService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineInjectable"]({ token: BookService, factory: BookService.ɵfac });


/***/ }),

/***/ "+UQt":
/*!**************************************!*\
  !*** ./src/app/models/book.model.ts ***!
  \**************************************/
/*! exports provided: BookModel */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "BookModel", function() { return BookModel; });
class BookModel {
}


/***/ }),

/***/ "/AxE":
/*!*******************************************************!*\
  !*** ./src/app/store/book-store/book-store.module.ts ***!
  \*******************************************************/
/*! exports provided: BookStoreModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "BookStoreModule", function() { return BookStoreModule; });
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/common */ "ofXK");
/* harmony import */ var _ngrx_effects__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @ngrx/effects */ "9jGm");
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _effects__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./effects */ "CxQm");
/* harmony import */ var _reducer__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./reducer */ "qd8X");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/core */ "fXoL");








class BookStoreModule {
}
BookStoreModule.ɵmod = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdefineNgModule"]({ type: BookStoreModule });
BookStoreModule.ɵinj = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdefineInjector"]({ factory: function BookStoreModule_Factory(t) { return new (t || BookStoreModule)(); }, providers: [_effects__WEBPACK_IMPORTED_MODULE_3__["BookEffect"]], imports: [[
            _angular_common__WEBPACK_IMPORTED_MODULE_0__["CommonModule"],
            _ngrx_store__WEBPACK_IMPORTED_MODULE_2__["StoreModule"].forFeature('books', _reducer__WEBPACK_IMPORTED_MODULE_4__["bookReducer"]),
            _ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["EffectsModule"].forFeature([_effects__WEBPACK_IMPORTED_MODULE_3__["BookEffect"]]),
        ]] });
(function () { (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵsetNgModuleScope"](BookStoreModule, { imports: [_angular_common__WEBPACK_IMPORTED_MODULE_0__["CommonModule"], _ngrx_store__WEBPACK_IMPORTED_MODULE_2__["StoreFeatureModule"], _ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["EffectsFeatureModule"]] }); })();


/***/ }),

/***/ 0:
/*!***************************!*\
  !*** multi ./src/main.ts ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! C:\z.Projects\Otus\spring-2020-11\2020-11-otus-spring-konovalov\spring-10\src\client\src\main.ts */"zUnb");


/***/ }),

/***/ "39v3":
/*!************************************************!*\
  !*** ./src/app/store/comment-store/actions.ts ***!
  \************************************************/
/*! exports provided: CommentActionTypes, LoadComments, LoadCommentsSuccess, LoadCommentsFail, LoadComment, LoadCommentSuccess, LoadCommentFail, CreateComment, CreateCommentSuccess, CreateCommentFail, UpdateComment, UpdateCommentSuccess, UpdateCommentFail, DeleteComment, DeleteCommentSuccess, DeleteCommentFail */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CommentActionTypes", function() { return CommentActionTypes; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadComments", function() { return LoadComments; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadCommentsSuccess", function() { return LoadCommentsSuccess; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadCommentsFail", function() { return LoadCommentsFail; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadComment", function() { return LoadComment; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadCommentSuccess", function() { return LoadCommentSuccess; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadCommentFail", function() { return LoadCommentFail; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CreateComment", function() { return CreateComment; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CreateCommentSuccess", function() { return CreateCommentSuccess; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CreateCommentFail", function() { return CreateCommentFail; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UpdateComment", function() { return UpdateComment; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UpdateCommentSuccess", function() { return UpdateCommentSuccess; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UpdateCommentFail", function() { return UpdateCommentFail; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DeleteComment", function() { return DeleteComment; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DeleteCommentSuccess", function() { return DeleteCommentSuccess; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DeleteCommentFail", function() { return DeleteCommentFail; });
var CommentActionTypes;
(function (CommentActionTypes) {
    CommentActionTypes["LOAD_COMMENTS"] = "[Comment/API Load Comments Request";
    CommentActionTypes["LOAD_COMMENTS_SUCCESS"] = "[Comment/API Load Comments Success";
    CommentActionTypes["LOAD_COMMENTS_FAIL"] = "[Comment/API Load Comments Failure";
    CommentActionTypes["LOAD_COMMENT"] = "[Comment/API Load Comment Request";
    CommentActionTypes["LOAD_COMMENT_SUCCESS"] = "[Comment/API Load Comment Success";
    CommentActionTypes["LOAD_COMMENT_FAIL"] = "[Comment/API Load Comment Failure";
    CommentActionTypes["CREATE_COMMENT"] = "[Comment/API Create Comment Request";
    CommentActionTypes["CREATE_COMMENT_SUCCESS"] = "[Comment/API Create Comment Success";
    CommentActionTypes["CREATE_COMMENT_FAIL"] = "[Comment/API Create Comment Failure";
    CommentActionTypes["UPDATE_COMMENT"] = "[Comment/API Update Comment Request";
    CommentActionTypes["UPDATE_COMMENT_SUCCESS"] = "[Comment/API Update Comment Success";
    CommentActionTypes["UPDATE_COMMENT_FAIL"] = "[Comment/API Update Comment Failure";
    CommentActionTypes["DELETE_COMMENT"] = "[Comment/API Delete Comment Request";
    CommentActionTypes["DELETE_COMMENT_SUCCESS"] = "[Comment/API Delete Comment Success";
    CommentActionTypes["DELETE_COMMENT_FAIL"] = "[Comment/API Delete Comment Failure";
})(CommentActionTypes || (CommentActionTypes = {}));
class LoadComments {
    constructor() {
        this.type = CommentActionTypes.LOAD_COMMENTS;
    }
}
class LoadCommentsSuccess {
    constructor(payload) {
        this.payload = payload;
        this.type = CommentActionTypes.LOAD_COMMENTS_SUCCESS;
    }
}
class LoadCommentsFail {
    constructor(payload) {
        this.payload = payload;
        this.type = CommentActionTypes.LOAD_COMMENTS_FAIL;
    }
}
//
class LoadComment {
    constructor(payload) {
        this.payload = payload;
        this.type = CommentActionTypes.LOAD_COMMENT;
    }
}
class LoadCommentSuccess {
    constructor(payload) {
        this.payload = payload;
        this.type = CommentActionTypes.LOAD_COMMENT_SUCCESS;
    }
}
class LoadCommentFail {
    constructor(payload) {
        this.payload = payload;
        this.type = CommentActionTypes.LOAD_COMMENT_FAIL;
    }
}
//
class CreateComment {
    constructor(payload) {
        this.payload = payload;
        this.type = CommentActionTypes.CREATE_COMMENT;
    }
}
class CreateCommentSuccess {
    constructor(payload) {
        this.payload = payload;
        this.type = CommentActionTypes.CREATE_COMMENT_SUCCESS;
    }
}
class CreateCommentFail {
    constructor(payload) {
        this.payload = payload;
        this.type = CommentActionTypes.CREATE_COMMENT_FAIL;
    }
}
//
class UpdateComment {
    constructor(payload) {
        this.payload = payload;
        this.type = CommentActionTypes.UPDATE_COMMENT;
    }
}
class UpdateCommentSuccess {
    constructor(payload) {
        this.payload = payload;
        this.type = CommentActionTypes.UPDATE_COMMENT_SUCCESS;
    }
}
class UpdateCommentFail {
    constructor(payload) {
        this.payload = payload;
        this.type = CommentActionTypes.UPDATE_COMMENT_FAIL;
    }
}
//
class DeleteComment {
    constructor(payload) {
        this.payload = payload;
        this.type = CommentActionTypes.DELETE_COMMENT;
    }
}
class DeleteCommentSuccess {
    constructor(payload) {
        this.payload = payload;
        this.type = CommentActionTypes.DELETE_COMMENT_SUCCESS;
    }
}
class DeleteCommentFail {
    constructor(payload) {
        this.payload = payload;
        this.type = CommentActionTypes.DELETE_COMMENT_FAIL;
    }
}


/***/ }),

/***/ "3pdl":
/*!*******************************************!*\
  !*** ./src/app/services/genre.service.ts ***!
  \*******************************************/
/*! exports provided: GenreService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "GenreService", function() { return GenreService; });
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! rxjs/operators */ "kU1M");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "tk/3");



class GenreService {
    constructor(http) {
        this.http = http;
    }
    loadGenres() {
        return this.http
            .get('/api/genre')
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_0__["map"])((data) => data));
    }
    loadGenreById(payload) {
        return this.http
            .get(`/api/genre/${payload}`)
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_0__["map"])((data) => data));
    }
    createGenre(payload) {
        return this.http
            .post('/api/genre', payload)
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_0__["map"])((data) => data));
    }
    updateGenre(payload) {
        return this.http
            .put(`/api/genre/${payload.id}`, payload)
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_0__["map"])((data) => data));
    }
    deleteGenre(payload) {
        return this.http
            .delete(`/api/genre/${payload}`)
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_0__["map"])((data) => data));
    }
}
GenreService.ɵfac = function GenreService_Factory(t) { return new (t || GenreService)(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵinject"](_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"])); };
GenreService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineInjectable"]({ token: GenreService, factory: GenreService.ɵfac });


/***/ }),

/***/ "5W+m":
/*!*****************************************************************!*\
  !*** ./src/app/components/author-edit/author-edit.component.ts ***!
  \*****************************************************************/
/*! exports provided: AuthorEditComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AuthorEditComponent", function() { return AuthorEditComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");

class AuthorEditComponent {
    constructor() { }
    ngOnInit() {
    }
}
AuthorEditComponent.ɵfac = function AuthorEditComponent_Factory(t) { return new (t || AuthorEditComponent)(); };
AuthorEditComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: AuthorEditComponent, selectors: [["app-author-edit"]], decls: 2, vars: 0, template: function AuthorEditComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "p");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1, "author-edit works!");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    } }, styles: ["\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJhdXRob3ItZWRpdC5jb21wb25lbnQuY3NzIn0= */"] });


/***/ }),

/***/ "9MH9":
/*!********************************************!*\
  !*** ./src/app/store/root-store.module.ts ***!
  \********************************************/
/*! exports provided: RootStoreModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "RootStoreModule", function() { return RootStoreModule; });
/* harmony import */ var _ngrx_effects__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @ngrx/effects */ "9jGm");
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _ngrx_store_devtools__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @ngrx/store-devtools */ "agSv");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../environments/environment */ "AytR");
/* harmony import */ var _author_store__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./author-store */ "JxL3");
/* harmony import */ var _book_store__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./book-store */ "w8RM");
/* harmony import */ var _comment_store__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./comment-store */ "RfW1");
/* harmony import */ var _genre_store__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./genre-store */ "y3Pi");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @angular/core */ "fXoL");












class RootStoreModule {
}
RootStoreModule.ɵmod = _angular_core__WEBPACK_IMPORTED_MODULE_8__["ɵɵdefineNgModule"]({ type: RootStoreModule });
RootStoreModule.ɵinj = _angular_core__WEBPACK_IMPORTED_MODULE_8__["ɵɵdefineInjector"]({ factory: function RootStoreModule_Factory(t) { return new (t || RootStoreModule)(); }, imports: [[
            _author_store__WEBPACK_IMPORTED_MODULE_4__["AuthorStoreModule"],
            _book_store__WEBPACK_IMPORTED_MODULE_5__["BookStoreModule"],
            _comment_store__WEBPACK_IMPORTED_MODULE_6__["CommentStoreModule"],
            _genre_store__WEBPACK_IMPORTED_MODULE_7__["GenreStoreModule"],
            _ngrx_store__WEBPACK_IMPORTED_MODULE_1__["StoreModule"].forRoot({}, {
                runtimeChecks: {
                    strictStateImmutability: true,
                    strictActionImmutability: true,
                    strictStateSerializability: true,
                    strictActionSerializability: true,
                },
            }),
            _ngrx_effects__WEBPACK_IMPORTED_MODULE_0__["EffectsModule"].forRoot([]),
            _ngrx_store_devtools__WEBPACK_IMPORTED_MODULE_2__["StoreDevtoolsModule"].instrument({
                maxAge: 25,
                logOnly: _environments_environment__WEBPACK_IMPORTED_MODULE_3__["environment"].production,
            }),
        ]] });
(function () { (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_8__["ɵɵsetNgModuleScope"](RootStoreModule, { imports: [_author_store__WEBPACK_IMPORTED_MODULE_4__["AuthorStoreModule"],
        _book_store__WEBPACK_IMPORTED_MODULE_5__["BookStoreModule"],
        _comment_store__WEBPACK_IMPORTED_MODULE_6__["CommentStoreModule"],
        _genre_store__WEBPACK_IMPORTED_MODULE_7__["GenreStoreModule"], _ngrx_store__WEBPACK_IMPORTED_MODULE_1__["StoreRootModule"], _ngrx_effects__WEBPACK_IMPORTED_MODULE_0__["EffectsRootModule"], _ngrx_store_devtools__WEBPACK_IMPORTED_MODULE_2__["StoreDevtoolsModule"]] }); })();


/***/ }),

/***/ "AytR":
/*!*****************************************!*\
  !*** ./src/environments/environment.ts ***!
  \*****************************************/
/*! exports provided: environment */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "environment", function() { return environment; });
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
const environment = {
    production: false
};
/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.


/***/ }),

/***/ "Cd6r":
/*!*******************************************!*\
  !*** ./src/app/store/book-store/state.ts ***!
  \*******************************************/
/*! exports provided: bookAdapter, initialBookState */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "bookAdapter", function() { return bookAdapter; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "initialBookState", function() { return initialBookState; });
/* harmony import */ var _ngrx_entity__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @ngrx/entity */ "R0sL");

const bookAdapter = Object(_ngrx_entity__WEBPACK_IMPORTED_MODULE_0__["createEntityAdapter"])();
const initialBookState = bookAdapter.getInitialState({
    ids: [],
    entities: {},
    selectedBookId: null,
    loading: false,
    loaded: false,
    error: '',
});


/***/ }),

/***/ "CfTE":
/*!****************************************!*\
  !*** ./src/app/models/author.model.ts ***!
  \****************************************/
/*! exports provided: AuthorModel */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AuthorModel", function() { return AuthorModel; });
class AuthorModel {
}


/***/ }),

/***/ "Cgsz":
/*!*********************************************!*\
  !*** ./src/app/store/book-store/actions.ts ***!
  \*********************************************/
/*! exports provided: BookActionTypes, LoadBooks, LoadBooksSuccess, LoadBooksFail, LoadBook, LoadBookSuccess, LoadBookFail, CreateBook, CreateBookSuccess, CreateBookFail, UpdateBook, UpdateBookSuccess, UpdateBookFail, DeleteBook, DeleteBookSuccess, DeleteBookFail */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "BookActionTypes", function() { return BookActionTypes; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadBooks", function() { return LoadBooks; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadBooksSuccess", function() { return LoadBooksSuccess; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadBooksFail", function() { return LoadBooksFail; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadBook", function() { return LoadBook; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadBookSuccess", function() { return LoadBookSuccess; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadBookFail", function() { return LoadBookFail; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CreateBook", function() { return CreateBook; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CreateBookSuccess", function() { return CreateBookSuccess; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CreateBookFail", function() { return CreateBookFail; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UpdateBook", function() { return UpdateBook; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UpdateBookSuccess", function() { return UpdateBookSuccess; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UpdateBookFail", function() { return UpdateBookFail; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DeleteBook", function() { return DeleteBook; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DeleteBookSuccess", function() { return DeleteBookSuccess; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DeleteBookFail", function() { return DeleteBookFail; });
var BookActionTypes;
(function (BookActionTypes) {
    BookActionTypes["LOAD_BOOKS"] = "[Book/API Load Books Request";
    BookActionTypes["LOAD_BOOKS_SUCCESS"] = "[Book/API Load Books Success";
    BookActionTypes["LOAD_BOOKS_FAIL"] = "[Book/API Load Books Failure";
    BookActionTypes["LOAD_BOOK"] = "[Book/API Load Book Request";
    BookActionTypes["LOAD_BOOK_SUCCESS"] = "[Book/API Load Book Success";
    BookActionTypes["LOAD_BOOK_FAIL"] = "[Book/API Load Book Failure";
    BookActionTypes["CREATE_BOOK"] = "[Book/API Create Book Request";
    BookActionTypes["CREATE_BOOK_SUCCESS"] = "[Book/API Create Book Success";
    BookActionTypes["CREATE_BOOK_FAIL"] = "[Book/API Create Book Failure";
    BookActionTypes["UPDATE_BOOK"] = "[Book/API Update Book Request";
    BookActionTypes["UPDATE_BOOK_SUCCESS"] = "[Book/API Update Book Success";
    BookActionTypes["UPDATE_BOOK_FAIL"] = "[Book/API Update Book Failure";
    BookActionTypes["DELETE_BOOK"] = "[Book/API Delete Book Request";
    BookActionTypes["DELETE_BOOK_SUCCESS"] = "[Book/API Delete Book Success";
    BookActionTypes["DELETE_BOOK_FAIL"] = "[Book/API Delete Book Failure";
})(BookActionTypes || (BookActionTypes = {}));
class LoadBooks {
    constructor() {
        this.type = BookActionTypes.LOAD_BOOKS;
    }
}
class LoadBooksSuccess {
    constructor(payload) {
        this.payload = payload;
        this.type = BookActionTypes.LOAD_BOOKS_SUCCESS;
    }
}
class LoadBooksFail {
    constructor(payload) {
        this.payload = payload;
        this.type = BookActionTypes.LOAD_BOOKS_FAIL;
    }
}
//
class LoadBook {
    constructor(payload) {
        this.payload = payload;
        this.type = BookActionTypes.LOAD_BOOK;
    }
}
class LoadBookSuccess {
    constructor(payload) {
        this.payload = payload;
        this.type = BookActionTypes.LOAD_BOOK_SUCCESS;
    }
}
class LoadBookFail {
    constructor(payload) {
        this.payload = payload;
        this.type = BookActionTypes.LOAD_BOOK_FAIL;
    }
}
//
class CreateBook {
    constructor(payload) {
        this.payload = payload;
        this.type = BookActionTypes.CREATE_BOOK;
    }
}
class CreateBookSuccess {
    constructor(payload) {
        this.payload = payload;
        this.type = BookActionTypes.CREATE_BOOK_SUCCESS;
    }
}
class CreateBookFail {
    constructor(payload) {
        this.payload = payload;
        this.type = BookActionTypes.CREATE_BOOK_FAIL;
    }
}
//
class UpdateBook {
    constructor(payload) {
        this.payload = payload;
        this.type = BookActionTypes.UPDATE_BOOK;
    }
}
class UpdateBookSuccess {
    constructor(payload) {
        this.payload = payload;
        this.type = BookActionTypes.UPDATE_BOOK_SUCCESS;
    }
}
class UpdateBookFail {
    constructor(payload) {
        this.payload = payload;
        this.type = BookActionTypes.UPDATE_BOOK_FAIL;
    }
}
//
class DeleteBook {
    constructor(payload) {
        this.payload = payload;
        this.type = BookActionTypes.DELETE_BOOK;
    }
}
class DeleteBookSuccess {
    constructor(payload) {
        this.payload = payload;
        this.type = BookActionTypes.DELETE_BOOK_SUCCESS;
    }
}
class DeleteBookFail {
    constructor(payload) {
        this.payload = payload;
        this.type = BookActionTypes.DELETE_BOOK_FAIL;
    }
}


/***/ }),

/***/ "CxQm":
/*!*********************************************!*\
  !*** ./src/app/store/book-store/effects.ts ***!
  \*********************************************/
/*! exports provided: BookEffect */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "BookEffect", function() { return BookEffect; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "mrSG");
/* harmony import */ var _ngrx_effects__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @ngrx/effects */ "9jGm");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs */ "qCKp");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs/operators */ "kU1M");
/* harmony import */ var _actions__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./actions */ "Cgsz");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _services_book_service__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../../services/book.service */ "+9od");








class BookEffect {
    constructor(actions$, bookService) {
        this.actions$ = actions$;
        this.bookService = bookService;
        this.loadBooks$ = this.actions$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions__WEBPACK_IMPORTED_MODULE_4__["BookActionTypes"].LOAD_BOOKS), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])((action) => this.bookService.loadBooks().pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((books) => new _actions__WEBPACK_IMPORTED_MODULE_4__["LoadBooksSuccess"](books)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])((err) => Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["of"])(new _actions__WEBPACK_IMPORTED_MODULE_4__["LoadBooksFail"](`${new Date()}\n${JSON.stringify(err)}`))))));
        this.loadBook$ = this.actions$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions__WEBPACK_IMPORTED_MODULE_4__["BookActionTypes"].LOAD_BOOK), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])((action) => this.bookService.loadBookById(action.payload).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((book) => new _actions__WEBPACK_IMPORTED_MODULE_4__["LoadBookSuccess"](book)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])((err) => Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["of"])(new _actions__WEBPACK_IMPORTED_MODULE_4__["LoadBookFail"](`${new Date()}\n${JSON.stringify(err)}`))))));
        this.createBook$ = this.actions$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions__WEBPACK_IMPORTED_MODULE_4__["BookActionTypes"].CREATE_BOOK), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((action) => action.payload), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])((book) => this.bookService.createBook(book).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((newBook) => new _actions__WEBPACK_IMPORTED_MODULE_4__["CreateBookSuccess"](newBook)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])((err) => Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["of"])(new _actions__WEBPACK_IMPORTED_MODULE_4__["CreateBookFail"](`${new Date()}\n${JSON.stringify(err)}`))))));
        this.updateBook$ = this.actions$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions__WEBPACK_IMPORTED_MODULE_4__["BookActionTypes"].UPDATE_BOOK), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((action) => action.payload), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])((book) => this.bookService.updateBook(book).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((updateBook) => new _actions__WEBPACK_IMPORTED_MODULE_4__["UpdateBookSuccess"]({
            id: updateBook.id,
            changes: updateBook,
        })), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])((err) => Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["of"])(new _actions__WEBPACK_IMPORTED_MODULE_4__["UpdateBookFail"](`${new Date()}\n${JSON.stringify(err)}`))))));
        this.deleteBook$ = this.actions$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions__WEBPACK_IMPORTED_MODULE_4__["BookActionTypes"].DELETE_BOOK), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((action) => action.payload), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])((id) => this.bookService.deleteBook(id).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])(() => new _actions__WEBPACK_IMPORTED_MODULE_4__["DeleteBookSuccess"](id)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])((err) => Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["of"])(new _actions__WEBPACK_IMPORTED_MODULE_4__["DeleteBookFail"](`${new Date()}\n${JSON.stringify(err)}`))))));
    }
}
BookEffect.ɵfac = function BookEffect_Factory(t) { return new (t || BookEffect)(_angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵinject"](_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Actions"]), _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵinject"](_services_book_service__WEBPACK_IMPORTED_MODULE_6__["BookService"])); };
BookEffect.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdefineInjectable"]({ token: BookEffect, factory: BookEffect.ɵfac });
Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Effect"])()
], BookEffect.prototype, "loadBooks$", void 0);
Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Effect"])()
], BookEffect.prototype, "loadBook$", void 0);
Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Effect"])()
], BookEffect.prototype, "createBook$", void 0);
Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Effect"])()
], BookEffect.prototype, "updateBook$", void 0);
Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Effect"])()
], BookEffect.prototype, "deleteBook$", void 0);


/***/ }),

/***/ "GE9g":
/*!*********************************************************!*\
  !*** ./src/app/store/genre-store/genre-store.module.ts ***!
  \*********************************************************/
/*! exports provided: GenreStoreModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "GenreStoreModule", function() { return GenreStoreModule; });
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/common */ "ofXK");
/* harmony import */ var _ngrx_effects__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @ngrx/effects */ "9jGm");
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _effects__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./effects */ "cLrz");
/* harmony import */ var _reducer__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./reducer */ "d4cG");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/core */ "fXoL");








class GenreStoreModule {
}
GenreStoreModule.ɵmod = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdefineNgModule"]({ type: GenreStoreModule });
GenreStoreModule.ɵinj = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdefineInjector"]({ factory: function GenreStoreModule_Factory(t) { return new (t || GenreStoreModule)(); }, providers: [_effects__WEBPACK_IMPORTED_MODULE_3__["GenreEffect"]], imports: [[
            _angular_common__WEBPACK_IMPORTED_MODULE_0__["CommonModule"],
            _ngrx_store__WEBPACK_IMPORTED_MODULE_2__["StoreModule"].forFeature('genres', _reducer__WEBPACK_IMPORTED_MODULE_4__["genreReducer"]),
            _ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["EffectsModule"].forFeature([_effects__WEBPACK_IMPORTED_MODULE_3__["GenreEffect"]]),
        ]] });
(function () { (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵsetNgModuleScope"](GenreStoreModule, { imports: [_angular_common__WEBPACK_IMPORTED_MODULE_0__["CommonModule"], _ngrx_store__WEBPACK_IMPORTED_MODULE_2__["StoreFeatureModule"], _ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["EffectsFeatureModule"]] }); })();


/***/ }),

/***/ "GGgI":
/*!*************************************************************!*\
  !*** ./src/app/components/books-tab/books-tab.component.ts ***!
  \*************************************************************/
/*! exports provided: BooksTabComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "BooksTabComponent", function() { return BooksTabComponent; });
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! rxjs */ "qCKp");
/* harmony import */ var _models_book_model__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../models/book.model */ "+UQt");
/* harmony import */ var _store_author_store__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../store/author-store */ "JxL3");
/* harmony import */ var _store_book_store__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../../store/book-store */ "w8RM");
/* harmony import */ var _store_genre_store__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../../store/genre-store */ "y3Pi");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var primeng_api__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! primeng/api */ "7zfz");
/* harmony import */ var primeng_table__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! primeng/table */ "rEr+");
/* harmony import */ var _book_edit_book_edit_component__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ../book-edit/book-edit.component */ "gD7T");
/* harmony import */ var primeng_toast__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! primeng/toast */ "Gxio");
/* harmony import */ var primeng_button__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! primeng/button */ "jIHw");













function BooksTabComponent_ng_template_2_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](0, "tr");
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](1, "th", 9);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵtext"](2, "ID");
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](3, "th", 10);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵtext"](4, "Book Name");
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](5, "th", 11);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵtext"](6, "Author Name");
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](7, "th", 11);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵtext"](8, "Genre");
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](9, "th", 11);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵtext"](10, "Manage");
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
} }
function BooksTabComponent_ng_template_3_Template(rf, ctx) { if (rf & 1) {
    const _r6 = _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](0, "tr");
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](1, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵtext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](3, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵtext"](4);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](5, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵtext"](6);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](7, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵtext"](8);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](9, "td", 12);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](10, "p-button", 13);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵlistener"]("onClick", function BooksTabComponent_ng_template_3_Template_p_button_onClick_10_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵrestoreView"](_r6); const book_r4 = ctx.$implicit; const ctx_r5 = _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵnextContext"](); return ctx_r5.editBook(book_r4); });
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](11, "p-button", 14);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵlistener"]("onClick", function BooksTabComponent_ng_template_3_Template_p_button_onClick_11_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵrestoreView"](_r6); const book_r4 = ctx.$implicit; const ctx_r7 = _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵnextContext"](); return ctx_r7.deleteBook(book_r4); });
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
} if (rf & 2) {
    const book_r4 = ctx.$implicit;
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵtextInterpolate"](book_r4.id);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵtextInterpolate"](book_r4.name);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵtextInterpolate"](book_r4.author.name);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵtextInterpolate"](book_r4.genres[0].name);
} }
function BooksTabComponent_ng_template_4_Template(rf, ctx) { if (rf & 1) {
    const _r9 = _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](0, "div", 15);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](1, "p-button", 16);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵlistener"]("onClick", function BooksTabComponent_ng_template_4_Template_p_button_onClick_1_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵrestoreView"](_r9); const ctx_r8 = _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵnextContext"](); return ctx_r8.addBook(); });
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
} }
function BooksTabComponent_ng_template_7_Template(rf, ctx) { if (rf & 1) {
    const _r12 = _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](0, "div", 17);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelement"](1, "em", 18);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](2, "h3");
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵtext"](3);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](4, "p");
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵtext"](5);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](6, "div", 19);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](7, "div", 20);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](8, "button", 21);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵlistener"]("click", function BooksTabComponent_ng_template_7_Template_button_click_8_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵrestoreView"](_r12); const ctx_r11 = _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵnextContext"](); return ctx_r11.onConfirm(ctx_r11.delAction); });
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](9, "div", 20);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](10, "button", 22);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵlistener"]("click", function BooksTabComponent_ng_template_7_Template_button_click_10_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵrestoreView"](_r12); const ctx_r13 = _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵnextContext"](); return ctx_r13.onReject(); });
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
} if (rf & 2) {
    const message_r10 = ctx.$implicit;
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵadvance"](3);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵtextInterpolate"](message_r10.summary);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵtextInterpolate"](message_r10.detail);
} }
class BooksTabComponent {
    constructor(bookStore$, authorStore$, genreStore$, messageService) {
        this.bookStore$ = bookStore$;
        this.authorStore$ = authorStore$;
        this.genreStore$ = genreStore$;
        this.messageService = messageService;
        this.subscriptions = new rxjs__WEBPACK_IMPORTED_MODULE_1__["Subscription"]();
        this.selBook = null;
        this.displayEditBookDialog = false;
        this.tmpBook = new _models_book_model__WEBPACK_IMPORTED_MODULE_2__["BookModel"]();
        this.books = [];
        this.authors = [];
        this.genres = [];
        this.action = null;
        this.delAction = null;
    }
    ngOnInit() {
        this.onError(this.bookStore$.pipe(Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["select"])(_store_book_store__WEBPACK_IMPORTED_MODULE_4__["BookStoreSelectors"].getError)));
        this.onError(this.authorStore$.pipe(Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["select"])(_store_author_store__WEBPACK_IMPORTED_MODULE_3__["AuthorStoreSelectors"].getError)));
        this.onError(this.genreStore$.pipe(Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["select"])(_store_genre_store__WEBPACK_IMPORTED_MODULE_5__["GenreStoreSelectors"].getError)));
        this.subscriptions.add(this.bookStore$
            .select(_store_book_store__WEBPACK_IMPORTED_MODULE_4__["BookStoreSelectors"].getBooks)
            .subscribe((data) => {
            this.books = data;
        }));
        this.subscriptions.add(this.authorStore$
            .select(_store_author_store__WEBPACK_IMPORTED_MODULE_3__["AuthorStoreSelectors"].getAuthors)
            .subscribe((data) => {
            this.authors = data;
        }));
        this.subscriptions.add(this.genreStore$
            .select(_store_genre_store__WEBPACK_IMPORTED_MODULE_5__["GenreStoreSelectors"].getGenres)
            .subscribe((data) => {
            this.genres = data;
        }));
        this.bookStore$.dispatch(new _store_book_store__WEBPACK_IMPORTED_MODULE_4__["BookStoreActions"].LoadBooks());
        this.authorStore$.dispatch(new _store_author_store__WEBPACK_IMPORTED_MODULE_3__["AuthorStoreActions"].LoadAuthors());
        this.genreStore$.dispatch(new _store_genre_store__WEBPACK_IMPORTED_MODULE_5__["GenreStoreActions"].LoadGenres());
    }
    ngOnDestroy() {
        this.subscriptions.unsubscribe();
    }
    // ------------------------------------------------------------------------------
    addBook() {
        this.action = 'ADD';
        this.tmpBook = new _models_book_model__WEBPACK_IMPORTED_MODULE_2__["BookModel"]();
        this.displayEditBookDialog = true;
    }
    editBook(book) {
        if (!book) {
            this.action = 'ADD';
        }
        else {
            this.action = 'EDIT';
        }
        this.selBook = book;
        this.tmpBook = book;
        this.displayEditBookDialog = true;
    }
    saveBookChanges(book) {
        if (this.action === 'ADD') {
            this.bookStore$.dispatch(new _store_book_store__WEBPACK_IMPORTED_MODULE_4__["BookStoreActions"].CreateBook(book));
        }
        else {
            this.bookStore$.dispatch(new _store_book_store__WEBPACK_IMPORTED_MODULE_4__["BookStoreActions"].UpdateBook(book));
        }
    }
    deleteBook(book) {
        this.tmpBook = book;
        this.delAction = 'delBook';
        this.messageService.clear();
        this.messageService.add({
            key: 'delConfirmDialog',
            sticky: true,
            severity: 'warn',
            summary: 'Вы уверены?',
            detail: 'Подтвердите удаление прошивки',
        });
    }
    // ------------------------------------------------------------------------------
    // Confirmation
    onConfirm(action) {
        if (this.tmpBook) {
            this.bookStore$.dispatch(new _store_book_store__WEBPACK_IMPORTED_MODULE_4__["BookStoreActions"].DeleteBook(this.tmpBook.id));
        }
        this.messageService.clear('delConfirmDialog');
    }
    onReject() {
        this.messageService.clear('delConfirmDialog');
    }
    onError(errorFlow$) {
        this.subscriptions.add(errorFlow$.subscribe((err) => {
            if (err && err.length > 0) {
                this.messageService.add({
                    key: 'serviceError',
                    severity: 'error',
                    summary: 'Ошибка сервиса',
                    detail: err.toString(),
                });
            }
        }));
    }
}
BooksTabComponent.ɵfac = function BooksTabComponent_Factory(t) { return new (t || BooksTabComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵdirectiveInject"](_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["Store"]), _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵdirectiveInject"](_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["Store"]), _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵdirectiveInject"](_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["Store"]), _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵdirectiveInject"](primeng_api__WEBPACK_IMPORTED_MODULE_7__["MessageService"])); };
BooksTabComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵdefineComponent"]({ type: BooksTabComponent, selectors: [["app-books-tab"]], decls: 9, vars: 7, consts: [[1, "book-table"], ["dataKey", "id", 3, "value"], ["pTemplate", "header"], ["pTemplate", "body"], ["pTemplate", "caption"], [3, "display", "book", "authors", "genres", "displayChange", "bookChange"], ["position", "center", "key", "delConfirmDialog", 3, "baseZIndex", "onClose"], ["pTemplate", "message"], ["position", "center", "key", "serviceError", 3, "baseZIndex"], ["scope", "col", 2, "width", "5%"], ["scope", "col", 2, "width", "35%"], ["scope", "col", 2, "width", "20%"], [2, "text-align", "center", "display", "flex"], ["icon", "pi pi-ellipsis-h", 1, "table-button", 3, "onClick"], ["icon", "pi pi-times", 1, "table-button", 3, "onClick"], [2, "text-align", "right"], ["icon", "pi pi-plus", 3, "onClick"], [2, "text-align", "center"], [1, "pi", "pi-exclamation-triangle", 2, "font-size", "3em"], [1, "ui-g", "ui-fluid"], [1, "ui-g-6"], ["type", "button", "pButton", "", "label", "\u0414\u0430", 1, "ui-button-success", 3, "click"], ["type", "button", "pButton", "", "label", "\u041D\u0435\u0442", 1, "ui-button-secondary", 3, "click"]], template: function BooksTabComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](0, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](1, "p-table", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵtemplate"](2, BooksTabComponent_ng_template_2_Template, 11, 0, "ng-template", 2);
        _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵtemplate"](3, BooksTabComponent_ng_template_3_Template, 12, 4, "ng-template", 3);
        _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵtemplate"](4, BooksTabComponent_ng_template_4_Template, 2, 0, "ng-template", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](5, "app-book-edit", 5);
        _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵlistener"]("displayChange", function BooksTabComponent_Template_app_book_edit_displayChange_5_listener($event) { return ctx.displayEditBookDialog = $event; })("bookChange", function BooksTabComponent_Template_app_book_edit_bookChange_5_listener($event) { return ctx.tmpBook = $event; })("bookChange", function BooksTabComponent_Template_app_book_edit_bookChange_5_listener($event) { return ctx.saveBookChanges($event); });
        _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementStart"](6, "p-toast", 6);
        _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵlistener"]("onClose", function BooksTabComponent_Template_p_toast_onClose_6_listener() { return ctx.onReject(); });
        _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵtemplate"](7, BooksTabComponent_ng_template_7_Template, 11, 2, "ng-template", 7);
        _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵelement"](8, "p-toast", 8);
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵproperty"]("value", ctx.books);
        _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵadvance"](4);
        _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵproperty"]("display", ctx.displayEditBookDialog)("book", ctx.tmpBook)("authors", ctx.authors)("genres", ctx.genres);
        _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵproperty"]("baseZIndex", 5000);
        _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵadvance"](2);
        _angular_core__WEBPACK_IMPORTED_MODULE_6__["ɵɵproperty"]("baseZIndex", 5000);
    } }, directives: [primeng_table__WEBPACK_IMPORTED_MODULE_8__["Table"], primeng_api__WEBPACK_IMPORTED_MODULE_7__["PrimeTemplate"], _book_edit_book_edit_component__WEBPACK_IMPORTED_MODULE_9__["BookEditComponent"], primeng_toast__WEBPACK_IMPORTED_MODULE_10__["Toast"], primeng_button__WEBPACK_IMPORTED_MODULE_11__["Button"], primeng_button__WEBPACK_IMPORTED_MODULE_11__["ButtonDirective"]], styles: [".book-table[_ngcontent-%COMP%]{\r\n  width: 1000px;\r\n}\r\n.container[_ngcontent-%COMP%]{\r\n  display: flex;\r\n  flex-direction: row;\r\n  justify-content: flex-start;\r\n}\r\n.button[_ngcontent-%COMP%]{\r\n  margin-left: 1px;\r\n}\r\n.table-button[_ngcontent-%COMP%]{\r\n  padding-right: 3px;\r\n}\r\n.label[_ngcontent-%COMP%] {\r\n  padding-top: 20px;\r\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImJvb2tzLXRhYi5jb21wb25lbnQuY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0VBQ0UsYUFBYTtBQUNmO0FBQ0E7RUFDRSxhQUFhO0VBQ2IsbUJBQW1CO0VBQ25CLDJCQUEyQjtBQUM3QjtBQUNBO0VBQ0UsZ0JBQWdCO0FBQ2xCO0FBQ0E7RUFDRSxrQkFBa0I7QUFDcEI7QUFDQTtFQUNFLGlCQUFpQjtBQUNuQiIsImZpbGUiOiJib29rcy10YWIuY29tcG9uZW50LmNzcyIsInNvdXJjZXNDb250ZW50IjpbIi5ib29rLXRhYmxle1xyXG4gIHdpZHRoOiAxMDAwcHg7XHJcbn1cclxuLmNvbnRhaW5lcntcclxuICBkaXNwbGF5OiBmbGV4O1xyXG4gIGZsZXgtZGlyZWN0aW9uOiByb3c7XHJcbiAganVzdGlmeS1jb250ZW50OiBmbGV4LXN0YXJ0O1xyXG59XHJcbi5idXR0b257XHJcbiAgbWFyZ2luLWxlZnQ6IDFweDtcclxufVxyXG4udGFibGUtYnV0dG9ue1xyXG4gIHBhZGRpbmctcmlnaHQ6IDNweDtcclxufVxyXG4ubGFiZWwge1xyXG4gIHBhZGRpbmctdG9wOiAyMHB4O1xyXG59XHJcbiJdfQ== */"] });


/***/ }),

/***/ "H2Fn":
/*!**********************************************!*\
  !*** ./src/app/store/genre-store/actions.ts ***!
  \**********************************************/
/*! exports provided: GenreActionTypes, LoadGenres, LoadGenresSuccess, LoadGenresFail, LoadGenre, LoadGenreSuccess, LoadGenreFail, CreateGenre, CreateGenreSuccess, CreateGenreFail, UpdateGenre, UpdateGenreSuccess, UpdateGenreFail, DeleteGenre, DeleteGenreSuccess, DeleteGenreFail */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "GenreActionTypes", function() { return GenreActionTypes; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadGenres", function() { return LoadGenres; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadGenresSuccess", function() { return LoadGenresSuccess; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadGenresFail", function() { return LoadGenresFail; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadGenre", function() { return LoadGenre; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadGenreSuccess", function() { return LoadGenreSuccess; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadGenreFail", function() { return LoadGenreFail; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CreateGenre", function() { return CreateGenre; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CreateGenreSuccess", function() { return CreateGenreSuccess; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CreateGenreFail", function() { return CreateGenreFail; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UpdateGenre", function() { return UpdateGenre; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UpdateGenreSuccess", function() { return UpdateGenreSuccess; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UpdateGenreFail", function() { return UpdateGenreFail; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DeleteGenre", function() { return DeleteGenre; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DeleteGenreSuccess", function() { return DeleteGenreSuccess; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DeleteGenreFail", function() { return DeleteGenreFail; });
var GenreActionTypes;
(function (GenreActionTypes) {
    GenreActionTypes["LOAD_GENRES"] = "[Genre/API Load Genres Request";
    GenreActionTypes["LOAD_GENRES_SUCCESS"] = "[Genre/API Load Genres Success";
    GenreActionTypes["LOAD_GENRES_FAIL"] = "[Genre/API Load Genres Failure";
    GenreActionTypes["LOAD_GENRE"] = "[Genre/API Load Genre Request";
    GenreActionTypes["LOAD_GENRE_SUCCESS"] = "[Genre/API Load Genre Success";
    GenreActionTypes["LOAD_GENRE_FAIL"] = "[Genre/API Load Genre Failure";
    GenreActionTypes["CREATE_GENRE"] = "[Genre/API Create Genre Request";
    GenreActionTypes["CREATE_GENRE_SUCCESS"] = "[Genre/API Create Genre Success";
    GenreActionTypes["CREATE_GENRE_FAIL"] = "[Genre/API Create Genre Failure";
    GenreActionTypes["UPDATE_GENRE"] = "[Genre/API Update Genre Request";
    GenreActionTypes["UPDATE_GENRE_SUCCESS"] = "[Genre/API Update Genre Success";
    GenreActionTypes["UPDATE_GENRE_FAIL"] = "[Genre/API Update Genre Failure";
    GenreActionTypes["DELETE_GENRE"] = "[Genre/API Delete Genre Request";
    GenreActionTypes["DELETE_GENRE_SUCCESS"] = "[Genre/API Delete Genre Success";
    GenreActionTypes["DELETE_GENRE_FAIL"] = "[Genre/API Delete Genre Failure";
})(GenreActionTypes || (GenreActionTypes = {}));
class LoadGenres {
    constructor() {
        this.type = GenreActionTypes.LOAD_GENRES;
    }
}
class LoadGenresSuccess {
    constructor(payload) {
        this.payload = payload;
        this.type = GenreActionTypes.LOAD_GENRES_SUCCESS;
    }
}
class LoadGenresFail {
    constructor(payload) {
        this.payload = payload;
        this.type = GenreActionTypes.LOAD_GENRES_FAIL;
    }
}
//
class LoadGenre {
    constructor(payload) {
        this.payload = payload;
        this.type = GenreActionTypes.LOAD_GENRE;
    }
}
class LoadGenreSuccess {
    constructor(payload) {
        this.payload = payload;
        this.type = GenreActionTypes.LOAD_GENRE_SUCCESS;
    }
}
class LoadGenreFail {
    constructor(payload) {
        this.payload = payload;
        this.type = GenreActionTypes.LOAD_GENRE_FAIL;
    }
}
//
class CreateGenre {
    constructor(payload) {
        this.payload = payload;
        this.type = GenreActionTypes.CREATE_GENRE;
    }
}
class CreateGenreSuccess {
    constructor(payload) {
        this.payload = payload;
        this.type = GenreActionTypes.CREATE_GENRE_SUCCESS;
    }
}
class CreateGenreFail {
    constructor(payload) {
        this.payload = payload;
        this.type = GenreActionTypes.CREATE_GENRE_FAIL;
    }
}
//
class UpdateGenre {
    constructor(payload) {
        this.payload = payload;
        this.type = GenreActionTypes.UPDATE_GENRE;
    }
}
class UpdateGenreSuccess {
    constructor(payload) {
        this.payload = payload;
        this.type = GenreActionTypes.UPDATE_GENRE_SUCCESS;
    }
}
class UpdateGenreFail {
    constructor(payload) {
        this.payload = payload;
        this.type = GenreActionTypes.UPDATE_GENRE_FAIL;
    }
}
//
class DeleteGenre {
    constructor(payload) {
        this.payload = payload;
        this.type = GenreActionTypes.DELETE_GENRE;
    }
}
class DeleteGenreSuccess {
    constructor(payload) {
        this.payload = payload;
        this.type = GenreActionTypes.DELETE_GENRE_SUCCESS;
    }
}
class DeleteGenreFail {
    constructor(payload) {
        this.payload = payload;
        this.type = GenreActionTypes.DELETE_GENRE_FAIL;
    }
}


/***/ }),

/***/ "HcfG":
/*!***************************************************************!*\
  !*** ./src/app/components/genres-tab/genres-tab.component.ts ***!
  \***************************************************************/
/*! exports provided: GenresTabComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "GenresTabComponent", function() { return GenresTabComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");

class GenresTabComponent {
    constructor() { }
    ngOnInit() {
    }
}
GenresTabComponent.ɵfac = function GenresTabComponent_Factory(t) { return new (t || GenresTabComponent)(); };
GenresTabComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: GenresTabComponent, selectors: [["app-genres-tab"]], decls: 2, vars: 0, template: function GenresTabComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "p");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1, "genres-tab works!");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    } }, styles: ["\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJnZW5yZXMtdGFiLmNvbXBvbmVudC5jc3MifQ== */"] });


/***/ }),

/***/ "HmbJ":
/*!**************************************************!*\
  !*** ./src/app/store/comment-store/selectors.ts ***!
  \**************************************************/
/*! exports provided: getComments, getCommentsLoading, getCommentsLoaded, getError, getCurrentCommentId */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getComments", function() { return getComments; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getCommentsLoading", function() { return getCommentsLoading; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getCommentsLoaded", function() { return getCommentsLoaded; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getError", function() { return getError; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getCurrentCommentId", function() { return getCurrentCommentId; });
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _state__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./state */ "JsJV");


const getCommentFeatureState = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createFeatureSelector"])('comments');
const getComments = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createSelector"])(getCommentFeatureState, _state__WEBPACK_IMPORTED_MODULE_1__["commentAdapter"].getSelectors().selectAll);
const getCommentsLoading = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createSelector"])(getCommentFeatureState, (state) => state.loading);
const getCommentsLoaded = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createSelector"])(getCommentFeatureState, (state) => state.loaded);
const getError = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createSelector"])(getCommentFeatureState, (state) => state.error);
const getCurrentCommentId = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createSelector"])(getCommentFeatureState, (state) => state.selectedCommentId);
// export const getCurrentComment = createSelector(
//   getCommentFeatureState,
//   getCurrentCommentId,
//   (state) => state.entities[state.selectedCommentId]
// );


/***/ }),

/***/ "IspA":
/*!***********************************************!*\
  !*** ./src/app/store/book-store/selectors.ts ***!
  \***********************************************/
/*! exports provided: getBooks, getBooksLoading, getBooksLoaded, getError, getCurrentBookId */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getBooks", function() { return getBooks; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getBooksLoading", function() { return getBooksLoading; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getBooksLoaded", function() { return getBooksLoaded; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getError", function() { return getError; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getCurrentBookId", function() { return getCurrentBookId; });
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _state__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./state */ "Cd6r");


const getBookFeatureState = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createFeatureSelector"])('books');
const getBooks = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createSelector"])(getBookFeatureState, _state__WEBPACK_IMPORTED_MODULE_1__["bookAdapter"].getSelectors().selectAll);
const getBooksLoading = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createSelector"])(getBookFeatureState, (state) => state.loading);
const getBooksLoaded = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createSelector"])(getBookFeatureState, (state) => state.loaded);
const getError = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createSelector"])(getBookFeatureState, (state) => state.error);
const getCurrentBookId = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createSelector"])(getBookFeatureState, (state) => state.selectedBookId);
// export const getCurrentBook = createSelector(
//   getBookFeatureState,
//   getCurrentBookId,
//   (state) => state.entities[state.selectedBookId]
// );


/***/ }),

/***/ "J/aB":
/*!***********************************************!*\
  !*** ./src/app/store/author-store/actions.ts ***!
  \***********************************************/
/*! exports provided: AuthorActionTypes, LoadAuthors, LoadAuthorsSuccess, LoadAuthorsFail, LoadAuthor, LoadAuthorSuccess, LoadAuthorFail, CreateAuthor, CreateAuthorSuccess, CreateAuthorFail, UpdateAuthor, UpdateAuthorSuccess, UpdateAuthorFail, DeleteAuthor, DeleteAuthorSuccess, DeleteAuthorFail */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AuthorActionTypes", function() { return AuthorActionTypes; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadAuthors", function() { return LoadAuthors; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadAuthorsSuccess", function() { return LoadAuthorsSuccess; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadAuthorsFail", function() { return LoadAuthorsFail; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadAuthor", function() { return LoadAuthor; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadAuthorSuccess", function() { return LoadAuthorSuccess; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoadAuthorFail", function() { return LoadAuthorFail; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CreateAuthor", function() { return CreateAuthor; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CreateAuthorSuccess", function() { return CreateAuthorSuccess; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CreateAuthorFail", function() { return CreateAuthorFail; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UpdateAuthor", function() { return UpdateAuthor; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UpdateAuthorSuccess", function() { return UpdateAuthorSuccess; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UpdateAuthorFail", function() { return UpdateAuthorFail; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DeleteAuthor", function() { return DeleteAuthor; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DeleteAuthorSuccess", function() { return DeleteAuthorSuccess; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DeleteAuthorFail", function() { return DeleteAuthorFail; });
var AuthorActionTypes;
(function (AuthorActionTypes) {
    AuthorActionTypes["LOAD_AUTHORS"] = "[Author/API Load Authors Request";
    AuthorActionTypes["LOAD_AUTHORS_SUCCESS"] = "[Author/API Load Authors Success";
    AuthorActionTypes["LOAD_AUTHORS_FAIL"] = "[Author/API Load Authors Failure";
    AuthorActionTypes["LOAD_AUTHOR"] = "[Author/API Load Author Request";
    AuthorActionTypes["LOAD_AUTHOR_SUCCESS"] = "[Author/API Load Author Success";
    AuthorActionTypes["LOAD_AUTHOR_FAIL"] = "[Author/API Load Author Failure";
    AuthorActionTypes["CREATE_AUTHOR"] = "[Author/API Create Author Request";
    AuthorActionTypes["CREATE_AUTHOR_SUCCESS"] = "[Author/API Create Author Success";
    AuthorActionTypes["CREATE_AUTHOR_FAIL"] = "[Author/API Create Author Failure";
    AuthorActionTypes["UPDATE_AUTHOR"] = "[Author/API Update Author Request";
    AuthorActionTypes["UPDATE_AUTHOR_SUCCESS"] = "[Author/API Update Author Success";
    AuthorActionTypes["UPDATE_AUTHOR_FAIL"] = "[Author/API Update Author Failure";
    AuthorActionTypes["DELETE_AUTHOR"] = "[Author/API Delete Author Request";
    AuthorActionTypes["DELETE_AUTHOR_SUCCESS"] = "[Author/API Delete Author Success";
    AuthorActionTypes["DELETE_AUTHOR_FAIL"] = "[Author/API Delete Author Failure";
})(AuthorActionTypes || (AuthorActionTypes = {}));
class LoadAuthors {
    constructor() {
        this.type = AuthorActionTypes.LOAD_AUTHORS;
    }
}
class LoadAuthorsSuccess {
    constructor(payload) {
        this.payload = payload;
        this.type = AuthorActionTypes.LOAD_AUTHORS_SUCCESS;
    }
}
class LoadAuthorsFail {
    constructor(payload) {
        this.payload = payload;
        this.type = AuthorActionTypes.LOAD_AUTHORS_FAIL;
    }
}
//
class LoadAuthor {
    constructor(payload) {
        this.payload = payload;
        this.type = AuthorActionTypes.LOAD_AUTHOR;
    }
}
class LoadAuthorSuccess {
    constructor(payload) {
        this.payload = payload;
        this.type = AuthorActionTypes.LOAD_AUTHOR_SUCCESS;
    }
}
class LoadAuthorFail {
    constructor(payload) {
        this.payload = payload;
        this.type = AuthorActionTypes.LOAD_AUTHOR_FAIL;
    }
}
//
class CreateAuthor {
    constructor(payload) {
        this.payload = payload;
        this.type = AuthorActionTypes.CREATE_AUTHOR;
    }
}
class CreateAuthorSuccess {
    constructor(payload) {
        this.payload = payload;
        this.type = AuthorActionTypes.CREATE_AUTHOR_SUCCESS;
    }
}
class CreateAuthorFail {
    constructor(payload) {
        this.payload = payload;
        this.type = AuthorActionTypes.CREATE_AUTHOR_FAIL;
    }
}
//
class UpdateAuthor {
    constructor(payload) {
        this.payload = payload;
        this.type = AuthorActionTypes.UPDATE_AUTHOR;
    }
}
class UpdateAuthorSuccess {
    constructor(payload) {
        this.payload = payload;
        this.type = AuthorActionTypes.UPDATE_AUTHOR_SUCCESS;
    }
}
class UpdateAuthorFail {
    constructor(payload) {
        this.payload = payload;
        this.type = AuthorActionTypes.UPDATE_AUTHOR_FAIL;
    }
}
//
class DeleteAuthor {
    constructor(payload) {
        this.payload = payload;
        this.type = AuthorActionTypes.DELETE_AUTHOR;
    }
}
class DeleteAuthorSuccess {
    constructor(payload) {
        this.payload = payload;
        this.type = AuthorActionTypes.DELETE_AUTHOR_SUCCESS;
    }
}
class DeleteAuthorFail {
    constructor(payload) {
        this.payload = payload;
        this.type = AuthorActionTypes.DELETE_AUTHOR_FAIL;
    }
}


/***/ }),

/***/ "J2KD":
/*!***************************************************************!*\
  !*** ./src/app/components/genre-edit/genre-edit.component.ts ***!
  \***************************************************************/
/*! exports provided: GenreEditComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "GenreEditComponent", function() { return GenreEditComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");

class GenreEditComponent {
    constructor() { }
    ngOnInit() {
    }
}
GenreEditComponent.ɵfac = function GenreEditComponent_Factory(t) { return new (t || GenreEditComponent)(); };
GenreEditComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: GenreEditComponent, selectors: [["app-genre-edit"]], decls: 2, vars: 0, template: function GenreEditComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "p");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1, "genre-edit works!");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    } }, styles: ["\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJnZW5yZS1lZGl0LmNvbXBvbmVudC5jc3MifQ== */"] });


/***/ }),

/***/ "JsJV":
/*!**********************************************!*\
  !*** ./src/app/store/comment-store/state.ts ***!
  \**********************************************/
/*! exports provided: commentAdapter, initialCommentState */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "commentAdapter", function() { return commentAdapter; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "initialCommentState", function() { return initialCommentState; });
/* harmony import */ var _ngrx_entity__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @ngrx/entity */ "R0sL");

const commentAdapter = Object(_ngrx_entity__WEBPACK_IMPORTED_MODULE_0__["createEntityAdapter"])();
const initialCommentState = commentAdapter.getInitialState({
    ids: [],
    entities: {},
    selectedCommentId: null,
    loading: false,
    loaded: false,
    error: '',
});


/***/ }),

/***/ "JxL3":
/*!*********************************************!*\
  !*** ./src/app/store/author-store/index.ts ***!
  \*********************************************/
/*! exports provided: AuthorStoreModule, AuthorStoreActions, AuthorStoreSelectors, AuthorStoreState */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _actions__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./actions */ "J/aB");
/* harmony reexport (module object) */ __webpack_require__.d(__webpack_exports__, "AuthorStoreActions", function() { return _actions__WEBPACK_IMPORTED_MODULE_0__; });
/* harmony import */ var _selectors__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./selectors */ "a8Gw");
/* harmony reexport (module object) */ __webpack_require__.d(__webpack_exports__, "AuthorStoreSelectors", function() { return _selectors__WEBPACK_IMPORTED_MODULE_1__; });
/* harmony import */ var _state__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./state */ "TlQj");
/* harmony reexport (module object) */ __webpack_require__.d(__webpack_exports__, "AuthorStoreState", function() { return _state__WEBPACK_IMPORTED_MODULE_2__; });
/* harmony import */ var _author_store_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./author-store.module */ "LxjE");
/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "AuthorStoreModule", function() { return _author_store_module__WEBPACK_IMPORTED_MODULE_3__["AuthorStoreModule"]; });








/***/ }),

/***/ "LmEr":
/*!*******************************************************!*\
  !*** ./src/app/components/footer/footer.component.ts ***!
  \*******************************************************/
/*! exports provided: FooterComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "FooterComponent", function() { return FooterComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "tyNb");
/* harmony import */ var primeng_button__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! primeng/button */ "jIHw");



class FooterComponent {
    constructor(router) {
        this.router = router;
    }
    ngOnInit() {
    }
    submit() {
        this.router.navigate(['/manage']).then();
    }
}
FooterComponent.ɵfac = function FooterComponent_Factory(t) { return new (t || FooterComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"])); };
FooterComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: FooterComponent, selectors: [["app-footer"]], decls: 5, vars: 0, consts: [[1, "footer"], [1, "p-grid"], [1, "p-col-10"], [1, "p-col-2"], ["type", "button", "label", "\u0423\u043F\u0440\u0430\u0432\u043B\u0435\u043D\u0438\u0435", 1, "button", 3, "onClick"]], template: function FooterComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](2, "div", 2);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](3, "div", 3);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](4, "p-button", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("onClick", function FooterComponent_Template_p_button_onClick_4_listener() { return ctx.submit(); });
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    } }, directives: [primeng_button__WEBPACK_IMPORTED_MODULE_2__["Button"]], styles: [".footer[_ngcontent-%COMP%] {\r\n  position: fixed;\r\n  width: 100%;\r\n  bottom: 0px;\r\n  padding: 0.5em;\r\n}\r\n\r\n.button[_ngcontent-%COMP%]{\r\n  margin-left: 3px;\r\n  float: right;\r\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImZvb3Rlci5jb21wb25lbnQuY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0VBQ0UsZUFBZTtFQUNmLFdBQVc7RUFDWCxXQUFXO0VBQ1gsY0FBYztBQUNoQjs7QUFFQTtFQUNFLGdCQUFnQjtFQUNoQixZQUFZO0FBQ2QiLCJmaWxlIjoiZm9vdGVyLmNvbXBvbmVudC5jc3MiLCJzb3VyY2VzQ29udGVudCI6WyIuZm9vdGVyIHtcclxuICBwb3NpdGlvbjogZml4ZWQ7XHJcbiAgd2lkdGg6IDEwMCU7XHJcbiAgYm90dG9tOiAwcHg7XHJcbiAgcGFkZGluZzogMC41ZW07XHJcbn1cclxuXHJcbi5idXR0b257XHJcbiAgbWFyZ2luLWxlZnQ6IDNweDtcclxuICBmbG9hdDogcmlnaHQ7XHJcbn1cclxuIl19 */"] });


/***/ }),

/***/ "LxjE":
/*!***********************************************************!*\
  !*** ./src/app/store/author-store/author-store.module.ts ***!
  \***********************************************************/
/*! exports provided: AuthorStoreModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AuthorStoreModule", function() { return AuthorStoreModule; });
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/common */ "ofXK");
/* harmony import */ var _ngrx_effects__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @ngrx/effects */ "9jGm");
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _effects__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./effects */ "whPw");
/* harmony import */ var _reducer__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./reducer */ "S/mB");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/core */ "fXoL");








class AuthorStoreModule {
}
AuthorStoreModule.ɵmod = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdefineNgModule"]({ type: AuthorStoreModule });
AuthorStoreModule.ɵinj = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdefineInjector"]({ factory: function AuthorStoreModule_Factory(t) { return new (t || AuthorStoreModule)(); }, providers: [_effects__WEBPACK_IMPORTED_MODULE_3__["AuthorEffect"]], imports: [[
            _angular_common__WEBPACK_IMPORTED_MODULE_0__["CommonModule"],
            _ngrx_store__WEBPACK_IMPORTED_MODULE_2__["StoreModule"].forFeature('authors', _reducer__WEBPACK_IMPORTED_MODULE_4__["authorReducer"]),
            _ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["EffectsModule"].forFeature([_effects__WEBPACK_IMPORTED_MODULE_3__["AuthorEffect"]]),
        ]] });
(function () { (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵsetNgModuleScope"](AuthorStoreModule, { imports: [_angular_common__WEBPACK_IMPORTED_MODULE_0__["CommonModule"], _ngrx_store__WEBPACK_IMPORTED_MODULE_2__["StoreFeatureModule"], _ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["EffectsFeatureModule"]] }); })();


/***/ }),

/***/ "MUMk":
/*!***************************************!*\
  !*** ./src/app/models/genre.model.ts ***!
  \***************************************/
/*! exports provided: GenreModel */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "GenreModel", function() { return GenreModel; });
class GenreModel {
}


/***/ }),

/***/ "MpPM":
/*!********************************************!*\
  !*** ./src/app/store/genre-store/state.ts ***!
  \********************************************/
/*! exports provided: genreAdapter, initialGenreState */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "genreAdapter", function() { return genreAdapter; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "initialGenreState", function() { return initialGenreState; });
/* harmony import */ var _ngrx_entity__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @ngrx/entity */ "R0sL");

const genreAdapter = Object(_ngrx_entity__WEBPACK_IMPORTED_MODULE_0__["createEntityAdapter"])();
const initialGenreState = genreAdapter.getInitialState({
    ids: [],
    entities: {},
    selectedGenreId: null,
    loading: false,
    loaded: false,
    error: '',
});


/***/ }),

/***/ "N+9M":
/*!********************************************!*\
  !*** ./src/app/services/author.service.ts ***!
  \********************************************/
/*! exports provided: AuthorService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AuthorService", function() { return AuthorService; });
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! rxjs/operators */ "kU1M");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "tk/3");



class AuthorService {
    constructor(http) {
        this.http = http;
    }
    loadAuthors() {
        return this.http
            .get('/api/author')
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_0__["map"])((data) => data));
    }
    loadAuthorById(payload) {
        return this.http
            .get(`/api/author/${payload}`)
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_0__["map"])((data) => data));
    }
    createAuthor(payload) {
        return this.http
            .post('/api/author', payload)
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_0__["map"])((data) => data));
    }
    updateAuthor(payload) {
        return this.http
            .put(`/api/author/${payload.id}`, payload)
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_0__["map"])((data) => data));
    }
    deleteAuthor(payload) {
        return this.http
            .delete(`/api/author/${payload}`)
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_0__["map"])((data) => data));
    }
}
AuthorService.ɵfac = function AuthorService_Factory(t) { return new (t || AuthorService)(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵinject"](_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"])); };
AuthorService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineInjectable"]({ token: AuthorService, factory: AuthorService.ɵfac });


/***/ }),

/***/ "NO+C":
/*!*******************************************************************!*\
  !*** ./src/app/components/comments-tab/comments-tab.component.ts ***!
  \*******************************************************************/
/*! exports provided: CommentsTabComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CommentsTabComponent", function() { return CommentsTabComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");

class CommentsTabComponent {
    constructor() { }
    ngOnInit() {
    }
}
CommentsTabComponent.ɵfac = function CommentsTabComponent_Factory(t) { return new (t || CommentsTabComponent)(); };
CommentsTabComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: CommentsTabComponent, selectors: [["app-comments-tab"]], decls: 2, vars: 0, template: function CommentsTabComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "p");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1, "comments-tab works!");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    } }, styles: ["\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJjb21tZW50cy10YWIuY29tcG9uZW50LmNzcyJ9 */"] });


/***/ }),

/***/ "RfW1":
/*!**********************************************!*\
  !*** ./src/app/store/comment-store/index.ts ***!
  \**********************************************/
/*! exports provided: CommentStoreModule, CommentStoreActions, CommentStoreSelectors, CommentStoreState */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _actions__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./actions */ "39v3");
/* harmony reexport (module object) */ __webpack_require__.d(__webpack_exports__, "CommentStoreActions", function() { return _actions__WEBPACK_IMPORTED_MODULE_0__; });
/* harmony import */ var _selectors__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./selectors */ "HmbJ");
/* harmony reexport (module object) */ __webpack_require__.d(__webpack_exports__, "CommentStoreSelectors", function() { return _selectors__WEBPACK_IMPORTED_MODULE_1__; });
/* harmony import */ var _state__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./state */ "JsJV");
/* harmony reexport (module object) */ __webpack_require__.d(__webpack_exports__, "CommentStoreState", function() { return _state__WEBPACK_IMPORTED_MODULE_2__; });
/* harmony import */ var _comment_store_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./comment-store.module */ "cyOE");
/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "CommentStoreModule", function() { return _comment_store_module__WEBPACK_IMPORTED_MODULE_3__["CommentStoreModule"]; });








/***/ }),

/***/ "S/mB":
/*!***********************************************!*\
  !*** ./src/app/store/author-store/reducer.ts ***!
  \***********************************************/
/*! exports provided: authorReducer */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "authorReducer", function() { return authorReducer; });
/* harmony import */ var _actions__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./actions */ "J/aB");
/* harmony import */ var _state__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./state */ "TlQj");


function authorReducer(state = _state__WEBPACK_IMPORTED_MODULE_1__["initialAuthorState"], action) {
    switch (action.type) {
        case _actions__WEBPACK_IMPORTED_MODULE_0__["AuthorActionTypes"].LOAD_AUTHORS_SUCCESS: {
            return _state__WEBPACK_IMPORTED_MODULE_1__["authorAdapter"].setAll(action.payload, Object.assign(Object.assign({}, state), { loading: false, loaded: true }));
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["AuthorActionTypes"].LOAD_AUTHORS_FAIL: {
            return Object.assign(Object.assign({}, state), { entities: {}, loading: false, loaded: false, error: action.payload });
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["AuthorActionTypes"].LOAD_AUTHOR_SUCCESS: {
            return _state__WEBPACK_IMPORTED_MODULE_1__["authorAdapter"].addOne(action.payload, Object.assign(Object.assign({}, state), { selectedAuthorId: action.payload.id }));
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["AuthorActionTypes"].LOAD_AUTHOR_FAIL: {
            return Object.assign(Object.assign({}, state), { error: action.payload });
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["AuthorActionTypes"].CREATE_AUTHOR_SUCCESS: {
            return _state__WEBPACK_IMPORTED_MODULE_1__["authorAdapter"].addOne(action.payload, state);
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["AuthorActionTypes"].CREATE_AUTHOR_FAIL: {
            return Object.assign(Object.assign({}, state), { error: action.payload });
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["AuthorActionTypes"].UPDATE_AUTHOR_SUCCESS: {
            return _state__WEBPACK_IMPORTED_MODULE_1__["authorAdapter"].updateOne(action.payload, state);
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["AuthorActionTypes"].UPDATE_AUTHOR_FAIL: {
            return Object.assign(Object.assign({}, state), { error: action.payload });
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["AuthorActionTypes"].DELETE_AUTHOR_SUCCESS: {
            return _state__WEBPACK_IMPORTED_MODULE_1__["authorAdapter"].removeOne(action.payload, state);
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["AuthorActionTypes"].DELETE_AUTHOR_FAIL: {
            return Object.assign(Object.assign({}, state), { error: action.payload });
        }
        default: {
            return state;
        }
    }
}


/***/ }),

/***/ "Sy1n":
/*!**********************************!*\
  !*** ./src/app/app.component.ts ***!
  \**********************************/
/*! exports provided: AppComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppComponent", function() { return AppComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "tyNb");
/* harmony import */ var _components_footer_footer_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./components/footer/footer.component */ "LmEr");



class AppComponent {
    constructor() {
        this.title = 'client';
    }
}
AppComponent.ɵfac = function AppComponent_Factory(t) { return new (t || AppComponent)(); };
AppComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: AppComponent, selectors: [["app-root"]], decls: 3, vars: 0, consts: [[1, "wrapper"]], template: function AppComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](1, "router-outlet");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](2, "app-footer");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    } }, directives: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterOutlet"], _components_footer_footer_component__WEBPACK_IMPORTED_MODULE_2__["FooterComponent"]], styles: [".wrapper[_ngcontent-%COMP%] {\r\n  background-image: url('background-1.jpg');\r\n  background-size: cover;\r\n  background-position: center center;\r\n  background-attachment: fixed;\r\n  background-repeat: no-repeat;\r\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImFwcC5jb21wb25lbnQuY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0VBQ0UseUNBQTJEO0VBQzNELHNCQUFzQjtFQUN0QixrQ0FBa0M7RUFDbEMsNEJBQTRCO0VBQzVCLDRCQUE0QjtBQUM5QiIsImZpbGUiOiJhcHAuY29tcG9uZW50LmNzcyIsInNvdXJjZXNDb250ZW50IjpbIi53cmFwcGVyIHtcclxuICBiYWNrZ3JvdW5kLWltYWdlOiB1cmwoXCJzcmMvYXNzZXRzL2ltYWdlcy9iYWNrZ3JvdW5kLTEuanBnXCIpO1xyXG4gIGJhY2tncm91bmQtc2l6ZTogY292ZXI7XHJcbiAgYmFja2dyb3VuZC1wb3NpdGlvbjogY2VudGVyIGNlbnRlcjtcclxuICBiYWNrZ3JvdW5kLWF0dGFjaG1lbnQ6IGZpeGVkO1xyXG4gIGJhY2tncm91bmQtcmVwZWF0OiBuby1yZXBlYXQ7XHJcbn1cclxuIl19 */"] });


/***/ }),

/***/ "TlQj":
/*!*********************************************!*\
  !*** ./src/app/store/author-store/state.ts ***!
  \*********************************************/
/*! exports provided: authorAdapter, initialAuthorState */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "authorAdapter", function() { return authorAdapter; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "initialAuthorState", function() { return initialAuthorState; });
/* harmony import */ var _ngrx_entity__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @ngrx/entity */ "R0sL");

const authorAdapter = Object(_ngrx_entity__WEBPACK_IMPORTED_MODULE_0__["createEntityAdapter"])();
const initialAuthorState = authorAdapter.getInitialState({
    ids: [],
    entities: {},
    selectedAuthorId: null,
    loading: false,
    loaded: false,
    error: '',
});


/***/ }),

/***/ "WFvv":
/*!*******************************************************************!*\
  !*** ./src/app/components/comment-edit/comment-edit.component.ts ***!
  \*******************************************************************/
/*! exports provided: CommentEditComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CommentEditComponent", function() { return CommentEditComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");

class CommentEditComponent {
    constructor() { }
    ngOnInit() {
    }
}
CommentEditComponent.ɵfac = function CommentEditComponent_Factory(t) { return new (t || CommentEditComponent)(); };
CommentEditComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: CommentEditComponent, selectors: [["app-comment-edit"]], decls: 2, vars: 0, template: function CommentEditComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "p");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1, "comment-edit works!");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    } }, styles: ["\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJjb21tZW50LWVkaXQuY29tcG9uZW50LmNzcyJ9 */"] });


/***/ }),

/***/ "ZAI4":
/*!*******************************!*\
  !*** ./src/app/app.module.ts ***!
  \*******************************/
/*! exports provided: AppModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppModule", function() { return AppModule; });
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/common */ "ofXK");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "tk/3");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/forms */ "3Pt+");
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/platform-browser */ "jhN1");
/* harmony import */ var _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/platform-browser/animations */ "R1ws");
/* harmony import */ var primeng_accordion__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! primeng/accordion */ "7LiV");
/* harmony import */ var primeng_api__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! primeng/api */ "7zfz");
/* harmony import */ var primeng_button__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! primeng/button */ "jIHw");
/* harmony import */ var primeng_card__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! primeng/card */ "QIUk");
/* harmony import */ var primeng_checkbox__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! primeng/checkbox */ "Ji6n");
/* harmony import */ var primeng_dialog__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! primeng/dialog */ "/RsI");
/* harmony import */ var primeng_dropdown__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! primeng/dropdown */ "arFO");
/* harmony import */ var primeng_dynamicdialog__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! primeng/dynamicdialog */ "J7/z");
/* harmony import */ var primeng_inputtext__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(/*! primeng/inputtext */ "7kUa");
/* harmony import */ var primeng_inputtextarea__WEBPACK_IMPORTED_MODULE_14__ = __webpack_require__(/*! primeng/inputtextarea */ "zFJ7");
/* harmony import */ var primeng_listbox__WEBPACK_IMPORTED_MODULE_15__ = __webpack_require__(/*! primeng/listbox */ "+07z");
/* harmony import */ var primeng_menu__WEBPACK_IMPORTED_MODULE_16__ = __webpack_require__(/*! primeng/menu */ "1SLH");
/* harmony import */ var primeng_message__WEBPACK_IMPORTED_MODULE_17__ = __webpack_require__(/*! primeng/message */ "FMpt");
/* harmony import */ var primeng_messages__WEBPACK_IMPORTED_MODULE_18__ = __webpack_require__(/*! primeng/messages */ "dts7");
/* harmony import */ var primeng_panel__WEBPACK_IMPORTED_MODULE_19__ = __webpack_require__(/*! primeng/panel */ "7CaW");
/* harmony import */ var primeng_panelmenu__WEBPACK_IMPORTED_MODULE_20__ = __webpack_require__(/*! primeng/panelmenu */ "kSmT");
/* harmony import */ var primeng_ripple__WEBPACK_IMPORTED_MODULE_21__ = __webpack_require__(/*! primeng/ripple */ "Q4Mo");
/* harmony import */ var primeng_table__WEBPACK_IMPORTED_MODULE_22__ = __webpack_require__(/*! primeng/table */ "rEr+");
/* harmony import */ var primeng_tabview__WEBPACK_IMPORTED_MODULE_23__ = __webpack_require__(/*! primeng/tabview */ "dPl2");
/* harmony import */ var primeng_toast__WEBPACK_IMPORTED_MODULE_24__ = __webpack_require__(/*! primeng/toast */ "Gxio");
/* harmony import */ var primeng_toolbar__WEBPACK_IMPORTED_MODULE_25__ = __webpack_require__(/*! primeng/toolbar */ "5EWq");
/* harmony import */ var primeng_tooltip__WEBPACK_IMPORTED_MODULE_26__ = __webpack_require__(/*! primeng/tooltip */ "xlun");
/* harmony import */ var _app_routing_module__WEBPACK_IMPORTED_MODULE_27__ = __webpack_require__(/*! ./app-routing.module */ "vY5A");
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_28__ = __webpack_require__(/*! ./app.component */ "Sy1n");
/* harmony import */ var _components_author_edit_author_edit_component__WEBPACK_IMPORTED_MODULE_29__ = __webpack_require__(/*! ./components/author-edit/author-edit.component */ "5W+m");
/* harmony import */ var _components_authors_tab_authors_tab_component__WEBPACK_IMPORTED_MODULE_30__ = __webpack_require__(/*! ./components/authors-tab/authors-tab.component */ "pXY/");
/* harmony import */ var _components_book_edit_book_edit_component__WEBPACK_IMPORTED_MODULE_31__ = __webpack_require__(/*! ./components/book-edit/book-edit.component */ "gD7T");
/* harmony import */ var _components_books_tab_books_tab_component__WEBPACK_IMPORTED_MODULE_32__ = __webpack_require__(/*! ./components/books-tab/books-tab.component */ "GGgI");
/* harmony import */ var _components_books_view_books_view_component__WEBPACK_IMPORTED_MODULE_33__ = __webpack_require__(/*! ./components/books-view/books-view.component */ "x34F");
/* harmony import */ var _components_comment_edit_comment_edit_component__WEBPACK_IMPORTED_MODULE_34__ = __webpack_require__(/*! ./components/comment-edit/comment-edit.component */ "WFvv");
/* harmony import */ var _components_comments_tab_comments_tab_component__WEBPACK_IMPORTED_MODULE_35__ = __webpack_require__(/*! ./components/comments-tab/comments-tab.component */ "NO+C");
/* harmony import */ var _components_footer_footer_component__WEBPACK_IMPORTED_MODULE_36__ = __webpack_require__(/*! ./components/footer/footer.component */ "LmEr");
/* harmony import */ var _components_genre_edit_genre_edit_component__WEBPACK_IMPORTED_MODULE_37__ = __webpack_require__(/*! ./components/genre-edit/genre-edit.component */ "J2KD");
/* harmony import */ var _components_genres_tab_genres_tab_component__WEBPACK_IMPORTED_MODULE_38__ = __webpack_require__(/*! ./components/genres-tab/genres-tab.component */ "HcfG");
/* harmony import */ var _components_home_page_home_page_component__WEBPACK_IMPORTED_MODULE_39__ = __webpack_require__(/*! ./components/home-page/home-page.component */ "atC9");
/* harmony import */ var _components_manage_page_manage_page_component__WEBPACK_IMPORTED_MODULE_40__ = __webpack_require__(/*! ./components/manage-page/manage-page.component */ "afxI");
/* harmony import */ var _services_author_service__WEBPACK_IMPORTED_MODULE_41__ = __webpack_require__(/*! ./services/author.service */ "N+9M");
/* harmony import */ var _services_book_service__WEBPACK_IMPORTED_MODULE_42__ = __webpack_require__(/*! ./services/book.service */ "+9od");
/* harmony import */ var _services_comment_service__WEBPACK_IMPORTED_MODULE_43__ = __webpack_require__(/*! ./services/comment.service */ "mxDV");
/* harmony import */ var _services_genre_service__WEBPACK_IMPORTED_MODULE_44__ = __webpack_require__(/*! ./services/genre.service */ "3pdl");
/* harmony import */ var _store_root_store_module__WEBPACK_IMPORTED_MODULE_45__ = __webpack_require__(/*! ./store/root-store.module */ "9MH9");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_46__ = __webpack_require__(/*! @angular/core */ "fXoL");















































class AppModule {
}
AppModule.ɵmod = _angular_core__WEBPACK_IMPORTED_MODULE_46__["ɵɵdefineNgModule"]({ type: AppModule, bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_28__["AppComponent"]] });
AppModule.ɵinj = _angular_core__WEBPACK_IMPORTED_MODULE_46__["ɵɵdefineInjector"]({ factory: function AppModule_Factory(t) { return new (t || AppModule)(); }, providers: [
        _services_author_service__WEBPACK_IMPORTED_MODULE_41__["AuthorService"],
        _services_book_service__WEBPACK_IMPORTED_MODULE_42__["BookService"],
        _services_comment_service__WEBPACK_IMPORTED_MODULE_43__["CommentService"],
        _services_genre_service__WEBPACK_IMPORTED_MODULE_44__["GenreService"],
        primeng_api__WEBPACK_IMPORTED_MODULE_6__["MessageService"],
    ], imports: [[
            _angular_platform_browser__WEBPACK_IMPORTED_MODULE_3__["BrowserModule"],
            _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_4__["BrowserAnimationsModule"],
            _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClientModule"],
            _app_routing_module__WEBPACK_IMPORTED_MODULE_27__["AppRoutingModule"],
            _store_root_store_module__WEBPACK_IMPORTED_MODULE_45__["RootStoreModule"],
            _angular_common__WEBPACK_IMPORTED_MODULE_0__["CommonModule"],
            _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormsModule"],
            _angular_forms__WEBPACK_IMPORTED_MODULE_2__["ReactiveFormsModule"],
            _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClientModule"],
            //  PrimeNG modules
            primeng_accordion__WEBPACK_IMPORTED_MODULE_5__["AccordionModule"],
            primeng_button__WEBPACK_IMPORTED_MODULE_7__["ButtonModule"],
            primeng_card__WEBPACK_IMPORTED_MODULE_8__["CardModule"],
            primeng_checkbox__WEBPACK_IMPORTED_MODULE_9__["CheckboxModule"],
            primeng_dialog__WEBPACK_IMPORTED_MODULE_10__["DialogModule"],
            primeng_dropdown__WEBPACK_IMPORTED_MODULE_11__["DropdownModule"],
            primeng_dynamicdialog__WEBPACK_IMPORTED_MODULE_12__["DynamicDialogModule"],
            primeng_inputtext__WEBPACK_IMPORTED_MODULE_13__["InputTextModule"],
            primeng_inputtextarea__WEBPACK_IMPORTED_MODULE_14__["InputTextareaModule"],
            primeng_listbox__WEBPACK_IMPORTED_MODULE_15__["ListboxModule"],
            primeng_menu__WEBPACK_IMPORTED_MODULE_16__["MenuModule"],
            primeng_panelmenu__WEBPACK_IMPORTED_MODULE_20__["PanelMenuModule"],
            primeng_panel__WEBPACK_IMPORTED_MODULE_19__["PanelModule"],
            primeng_tabview__WEBPACK_IMPORTED_MODULE_23__["TabViewModule"],
            primeng_table__WEBPACK_IMPORTED_MODULE_22__["TableModule"],
            primeng_toast__WEBPACK_IMPORTED_MODULE_24__["ToastModule"],
            primeng_toolbar__WEBPACK_IMPORTED_MODULE_25__["ToolbarModule"],
            primeng_tooltip__WEBPACK_IMPORTED_MODULE_26__["TooltipModule"],
            primeng_ripple__WEBPACK_IMPORTED_MODULE_21__["RippleModule"],
            primeng_messages__WEBPACK_IMPORTED_MODULE_18__["MessagesModule"],
            primeng_message__WEBPACK_IMPORTED_MODULE_17__["MessageModule"]
        ]] });
(function () { (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_46__["ɵɵsetNgModuleScope"](AppModule, { declarations: [_app_component__WEBPACK_IMPORTED_MODULE_28__["AppComponent"],
        _components_home_page_home_page_component__WEBPACK_IMPORTED_MODULE_39__["HomePageComponent"],
        _components_book_edit_book_edit_component__WEBPACK_IMPORTED_MODULE_31__["BookEditComponent"],
        _components_author_edit_author_edit_component__WEBPACK_IMPORTED_MODULE_29__["AuthorEditComponent"],
        _components_genre_edit_genre_edit_component__WEBPACK_IMPORTED_MODULE_37__["GenreEditComponent"],
        _components_comment_edit_comment_edit_component__WEBPACK_IMPORTED_MODULE_34__["CommentEditComponent"],
        _components_footer_footer_component__WEBPACK_IMPORTED_MODULE_36__["FooterComponent"],
        _components_manage_page_manage_page_component__WEBPACK_IMPORTED_MODULE_40__["ManagePageComponent"],
        _components_books_view_books_view_component__WEBPACK_IMPORTED_MODULE_33__["BooksViewComponent"],
        _components_authors_tab_authors_tab_component__WEBPACK_IMPORTED_MODULE_30__["AuthorsTabComponent"],
        _components_books_tab_books_tab_component__WEBPACK_IMPORTED_MODULE_32__["BooksTabComponent"],
        _components_comments_tab_comments_tab_component__WEBPACK_IMPORTED_MODULE_35__["CommentsTabComponent"],
        _components_genres_tab_genres_tab_component__WEBPACK_IMPORTED_MODULE_38__["GenresTabComponent"]], imports: [_angular_platform_browser__WEBPACK_IMPORTED_MODULE_3__["BrowserModule"],
        _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_4__["BrowserAnimationsModule"],
        _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClientModule"],
        _app_routing_module__WEBPACK_IMPORTED_MODULE_27__["AppRoutingModule"],
        _store_root_store_module__WEBPACK_IMPORTED_MODULE_45__["RootStoreModule"],
        _angular_common__WEBPACK_IMPORTED_MODULE_0__["CommonModule"],
        _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormsModule"],
        _angular_forms__WEBPACK_IMPORTED_MODULE_2__["ReactiveFormsModule"],
        _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClientModule"],
        //  PrimeNG modules
        primeng_accordion__WEBPACK_IMPORTED_MODULE_5__["AccordionModule"],
        primeng_button__WEBPACK_IMPORTED_MODULE_7__["ButtonModule"],
        primeng_card__WEBPACK_IMPORTED_MODULE_8__["CardModule"],
        primeng_checkbox__WEBPACK_IMPORTED_MODULE_9__["CheckboxModule"],
        primeng_dialog__WEBPACK_IMPORTED_MODULE_10__["DialogModule"],
        primeng_dropdown__WEBPACK_IMPORTED_MODULE_11__["DropdownModule"],
        primeng_dynamicdialog__WEBPACK_IMPORTED_MODULE_12__["DynamicDialogModule"],
        primeng_inputtext__WEBPACK_IMPORTED_MODULE_13__["InputTextModule"],
        primeng_inputtextarea__WEBPACK_IMPORTED_MODULE_14__["InputTextareaModule"],
        primeng_listbox__WEBPACK_IMPORTED_MODULE_15__["ListboxModule"],
        primeng_menu__WEBPACK_IMPORTED_MODULE_16__["MenuModule"],
        primeng_panelmenu__WEBPACK_IMPORTED_MODULE_20__["PanelMenuModule"],
        primeng_panel__WEBPACK_IMPORTED_MODULE_19__["PanelModule"],
        primeng_tabview__WEBPACK_IMPORTED_MODULE_23__["TabViewModule"],
        primeng_table__WEBPACK_IMPORTED_MODULE_22__["TableModule"],
        primeng_toast__WEBPACK_IMPORTED_MODULE_24__["ToastModule"],
        primeng_toolbar__WEBPACK_IMPORTED_MODULE_25__["ToolbarModule"],
        primeng_tooltip__WEBPACK_IMPORTED_MODULE_26__["TooltipModule"],
        primeng_ripple__WEBPACK_IMPORTED_MODULE_21__["RippleModule"],
        primeng_messages__WEBPACK_IMPORTED_MODULE_18__["MessagesModule"],
        primeng_message__WEBPACK_IMPORTED_MODULE_17__["MessageModule"]] }); })();


/***/ }),

/***/ "a8Gw":
/*!*************************************************!*\
  !*** ./src/app/store/author-store/selectors.ts ***!
  \*************************************************/
/*! exports provided: getAuthors, getAuthorsLoading, getAuthorsLoaded, getError, getCurrentAuthorId */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getAuthors", function() { return getAuthors; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getAuthorsLoading", function() { return getAuthorsLoading; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getAuthorsLoaded", function() { return getAuthorsLoaded; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getError", function() { return getError; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getCurrentAuthorId", function() { return getCurrentAuthorId; });
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _state__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./state */ "TlQj");


const getAuthorFeatureState = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createFeatureSelector"])('authors');
const getAuthors = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createSelector"])(getAuthorFeatureState, _state__WEBPACK_IMPORTED_MODULE_1__["authorAdapter"].getSelectors().selectAll);
const getAuthorsLoading = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createSelector"])(getAuthorFeatureState, (state) => state.loading);
const getAuthorsLoaded = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createSelector"])(getAuthorFeatureState, (state) => state.loaded);
const getError = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createSelector"])(getAuthorFeatureState, (state) => state.error);
const getCurrentAuthorId = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createSelector"])(getAuthorFeatureState, (state) => state.selectedAuthorId);
// export const getCurrentAuthor = createSelector(
//   getAuthorFeatureState,
//   getCurrentAuthorId,
//   (state) => state.entities[state.selectedAuthorId]
// );


/***/ }),

/***/ "afxI":
/*!*****************************************************************!*\
  !*** ./src/app/components/manage-page/manage-page.component.ts ***!
  \*****************************************************************/
/*! exports provided: ManagePageComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ManagePageComponent", function() { return ManagePageComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var primeng_panel__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! primeng/panel */ "7CaW");
/* harmony import */ var primeng_tabview__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! primeng/tabview */ "dPl2");
/* harmony import */ var _books_tab_books_tab_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../books-tab/books-tab.component */ "GGgI");
/* harmony import */ var _authors_tab_authors_tab_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../authors-tab/authors-tab.component */ "pXY/");
/* harmony import */ var _genres_tab_genres_tab_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../genres-tab/genres-tab.component */ "HcfG");






class ManagePageComponent {
    constructor() { }
    ngOnInit() {
    }
}
ManagePageComponent.ɵfac = function ManagePageComponent_Factory(t) { return new (t || ManagePageComponent)(); };
ManagePageComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: ManagePageComponent, selectors: [["app-manage-page"]], decls: 14, vars: 0, consts: [["id", "layout-content"], [1, "card", "p-grid", "p-align-center", "p-nogutter"], [1, "p-col"], [1, "p-col-10", "p-col-align-center"], ["header", "\u041A\u043D\u0438\u0433\u0438"], ["header", "\u0421\u043F\u0440\u0430\u0432\u043E\u0447\u043D\u0438\u043A \u0430\u0432\u0442\u043E\u0440\u043E\u0432"], ["header", "\u0421\u043F\u0440\u0430\u0432\u043E\u0447\u043D\u0438\u043A \u0436\u0430\u043D\u0440\u043E\u0432"], ["header", "\u041A\u043E\u043C\u043C\u0435\u043D\u0442\u0430\u0440\u0438\u0438"]], template: function ManagePageComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](2, "div", 2);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](3, "div", 3);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](4, "p-panel");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](5, "p-tabView");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](6, "p-tabPanel", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](7, "app-books-tab");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](8, "p-tabPanel", 5);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](9, "app-authors-tab");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](10, "p-tabPanel", 6);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](11, "app-genres-tab");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](12, "p-tabPanel", 7);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](13, "app-genres-tab");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    } }, directives: [primeng_panel__WEBPACK_IMPORTED_MODULE_1__["Panel"], primeng_tabview__WEBPACK_IMPORTED_MODULE_2__["TabView"], primeng_tabview__WEBPACK_IMPORTED_MODULE_2__["TabPanel"], _books_tab_books_tab_component__WEBPACK_IMPORTED_MODULE_3__["BooksTabComponent"], _authors_tab_authors_tab_component__WEBPACK_IMPORTED_MODULE_4__["AuthorsTabComponent"], _genres_tab_genres_tab_component__WEBPACK_IMPORTED_MODULE_5__["GenresTabComponent"]], styles: ["#layout-content[_ngcontent-%COMP%] {\r\n  margin-left: 400px;\r\n  margin-right: 400px;\r\n  padding-top: 70px;\r\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIm1hbmFnZS1wYWdlLmNvbXBvbmVudC5jc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7RUFDRSxrQkFBa0I7RUFDbEIsbUJBQW1CO0VBQ25CLGlCQUFpQjtBQUNuQiIsImZpbGUiOiJtYW5hZ2UtcGFnZS5jb21wb25lbnQuY3NzIiwic291cmNlc0NvbnRlbnQiOlsiI2xheW91dC1jb250ZW50IHtcclxuICBtYXJnaW4tbGVmdDogNDAwcHg7XHJcbiAgbWFyZ2luLXJpZ2h0OiA0MDBweDtcclxuICBwYWRkaW5nLXRvcDogNzBweDtcclxufVxyXG4iXX0= */"] });


/***/ }),

/***/ "atC9":
/*!*************************************************************!*\
  !*** ./src/app/components/home-page/home-page.component.ts ***!
  \*************************************************************/
/*! exports provided: HomePageComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "HomePageComponent", function() { return HomePageComponent; });
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/forms */ "3Pt+");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! rxjs */ "qCKp");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/router */ "tyNb");
/* harmony import */ var primeng_panel__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! primeng/panel */ "7CaW");
/* harmony import */ var primeng_inputtext__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! primeng/inputtext */ "7kUa");
/* harmony import */ var primeng_button__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! primeng/button */ "jIHw");
/* harmony import */ var primeng_ripple__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! primeng/ripple */ "Q4Mo");









class HomePageComponent {
    constructor(router, formBuilder) {
        this.router = router;
        this.formBuilder = formBuilder;
        this.subscriptions = new rxjs__WEBPACK_IMPORTED_MODULE_1__["Subscription"]();
        this.searchForm = this.formBuilder.group({
            searchText: ['', [_angular_forms__WEBPACK_IMPORTED_MODULE_0__["Validators"].required]],
        });
        this.clickSearch = false;
        this.clickGetAll = false;
        this.searchText = '';
    }
    ngOnInit() {
    }
    ngOnDestroy() {
        this.subscriptions.unsubscribe();
    }
    submitSearch() {
        var _a;
        if (this.clickSearch && this.searchForm.valid) {
            this.searchText = (_a = this.searchForm.get('searchText')) === null || _a === void 0 ? void 0 : _a.value;
            this.router.navigate(['/books-view']).then();
        }
        else if (this.clickGetAll) {
            this.router.navigate(['/books-view']).then();
        }
    }
    onClickSearchButton() {
        this.clickSearch = true;
        this.clickGetAll = false;
    }
    onClickGetAllButton() {
        this.clickSearch = false;
        this.clickGetAll = true;
    }
}
HomePageComponent.ɵfac = function HomePageComponent_Factory(t) { return new (t || HomePageComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵdirectiveInject"](_angular_router__WEBPACK_IMPORTED_MODULE_3__["Router"]), _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵdirectiveInject"](_angular_forms__WEBPACK_IMPORTED_MODULE_0__["FormBuilder"])); };
HomePageComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵdefineComponent"]({ type: HomePageComponent, selectors: [["app-home-page"]], decls: 15, vars: 1, consts: [[1, "p-grid", "p-align-center", "p-nogutter", "welcome-panel"], [1, "p-col-2"], [1, "p-col-5", "p-col-align-center"], [3, "formGroup", "ngSubmit"], [1, "welcome-text"], [1, "p-inputgroup"], ["id", "search-input", "type", "text", "pInputText", "", "placeholder", "\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0447\u0430\u0441\u0442\u044C \u043D\u0430\u0437\u0432\u0430\u043D\u0438\u044F \u043A\u043D\u0438\u0433\u0438 \u0434\u043B\u044F \u043F\u043E\u0438\u0441\u043A\u0430", "formControlName", "searchText"], ["type", "submit", "pButton", "", "pRipple", "", "label", "Search", 3, "click"], ["type", "submit", "pButton", "", "pRipple", "", "label", "GetAll", 3, "click"]], template: function HomePageComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](0, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelement"](1, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](2, "div", 2);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](3, "p-panel");
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](4, "form", 3);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵlistener"]("ngSubmit", function HomePageComponent_Template_form_ngSubmit_4_listener() { return ctx.submitSearch(); });
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](5, "div", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](6, "h3");
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](7, "\u0414\u043E\u0431\u0440\u043E \u043F\u043E\u0436\u0430\u043B\u043E\u0432\u0430\u0442\u044C \u0431\u0438\u0431\u043B\u0438\u043E\u0442\u0435\u043A\u0443");
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](8, "h1");
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵtext"](9, "OTUS");
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](10, "div", 5);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelement"](11, "input", 6);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](12, "button", 7);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵlistener"]("click", function HomePageComponent_Template_button_click_12_listener() { return ctx.onClickSearchButton(); });
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementStart"](13, "button", 8);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵlistener"]("click", function HomePageComponent_Template_button_click_13_listener() { return ctx.onClickGetAllButton(); });
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelement"](14, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵelementEnd"]();
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵadvance"](4);
        _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵproperty"]("formGroup", ctx.searchForm);
    } }, directives: [primeng_panel__WEBPACK_IMPORTED_MODULE_4__["Panel"], _angular_forms__WEBPACK_IMPORTED_MODULE_0__["ɵangular_packages_forms_forms_y"], _angular_forms__WEBPACK_IMPORTED_MODULE_0__["NgControlStatusGroup"], _angular_forms__WEBPACK_IMPORTED_MODULE_0__["FormGroupDirective"], _angular_forms__WEBPACK_IMPORTED_MODULE_0__["DefaultValueAccessor"], primeng_inputtext__WEBPACK_IMPORTED_MODULE_5__["InputText"], _angular_forms__WEBPACK_IMPORTED_MODULE_0__["NgControlStatus"], _angular_forms__WEBPACK_IMPORTED_MODULE_0__["FormControlName"], primeng_button__WEBPACK_IMPORTED_MODULE_6__["ButtonDirective"], primeng_ripple__WEBPACK_IMPORTED_MODULE_7__["Ripple"]], styles: [".welcome-panel[_ngcontent-%COMP%] {\r\n  padding-top: 250px;\r\n  width: 800px;\r\n  margin: 0 auto;\r\n  min-width: 600px\r\n}\r\n\r\n.welcome-text[_ngcontent-%COMP%] {\r\n  text-align: center;\r\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImhvbWUtcGFnZS5jb21wb25lbnQuY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0VBQ0Usa0JBQWtCO0VBQ2xCLFlBQVk7RUFDWixjQUFjO0VBQ2Q7QUFDRjs7QUFFQTtFQUNFLGtCQUFrQjtBQUNwQiIsImZpbGUiOiJob21lLXBhZ2UuY29tcG9uZW50LmNzcyIsInNvdXJjZXNDb250ZW50IjpbIi53ZWxjb21lLXBhbmVsIHtcclxuICBwYWRkaW5nLXRvcDogMjUwcHg7XHJcbiAgd2lkdGg6IDgwMHB4O1xyXG4gIG1hcmdpbjogMCBhdXRvO1xyXG4gIG1pbi13aWR0aDogNjAwcHhcclxufVxyXG5cclxuLndlbGNvbWUtdGV4dCB7XHJcbiAgdGV4dC1hbGlnbjogY2VudGVyO1xyXG59XHJcbiJdfQ== */"] });


/***/ }),

/***/ "cLrz":
/*!**********************************************!*\
  !*** ./src/app/store/genre-store/effects.ts ***!
  \**********************************************/
/*! exports provided: GenreEffect */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "GenreEffect", function() { return GenreEffect; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "mrSG");
/* harmony import */ var _ngrx_effects__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @ngrx/effects */ "9jGm");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs */ "qCKp");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs/operators */ "kU1M");
/* harmony import */ var _actions__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./actions */ "H2Fn");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _services_genre_service__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../../services/genre.service */ "3pdl");








class GenreEffect {
    constructor(actions$, genreService) {
        this.actions$ = actions$;
        this.genreService = genreService;
        this.loadGenres$ = this.actions$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions__WEBPACK_IMPORTED_MODULE_4__["GenreActionTypes"].LOAD_GENRES), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])((action) => this.genreService.loadGenres().pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((genres) => new _actions__WEBPACK_IMPORTED_MODULE_4__["LoadGenresSuccess"](genres)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])((err) => Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["of"])(new _actions__WEBPACK_IMPORTED_MODULE_4__["LoadGenresFail"](`${new Date()}\n${JSON.stringify(err)}`))))));
        this.loadGenre$ = this.actions$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions__WEBPACK_IMPORTED_MODULE_4__["GenreActionTypes"].LOAD_GENRE), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])((action) => this.genreService.loadGenreById(action.payload).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((genre) => new _actions__WEBPACK_IMPORTED_MODULE_4__["LoadGenreSuccess"](genre)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])((err) => Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["of"])(new _actions__WEBPACK_IMPORTED_MODULE_4__["LoadGenreFail"](`${new Date()}\n${JSON.stringify(err)}`))))));
        this.createGenre$ = this.actions$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions__WEBPACK_IMPORTED_MODULE_4__["GenreActionTypes"].CREATE_GENRE), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((action) => action.payload), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])((genre) => this.genreService.createGenre(genre).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((newGenre) => new _actions__WEBPACK_IMPORTED_MODULE_4__["CreateGenreSuccess"](newGenre)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])((err) => Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["of"])(new _actions__WEBPACK_IMPORTED_MODULE_4__["CreateGenreFail"](`${new Date()}\n${JSON.stringify(err)}`))))));
        this.updateGenre$ = this.actions$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions__WEBPACK_IMPORTED_MODULE_4__["GenreActionTypes"].UPDATE_GENRE), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((action) => action.payload), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])((genre) => this.genreService.updateGenre(genre).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((updateGenre) => new _actions__WEBPACK_IMPORTED_MODULE_4__["UpdateGenreSuccess"]({
            id: updateGenre.id,
            changes: updateGenre,
        })), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])((err) => Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["of"])(new _actions__WEBPACK_IMPORTED_MODULE_4__["UpdateGenreFail"](`${new Date()}\n${JSON.stringify(err)}`))))));
        this.deleteGenre$ = this.actions$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions__WEBPACK_IMPORTED_MODULE_4__["GenreActionTypes"].DELETE_GENRE), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((action) => action.payload), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])((id) => this.genreService.deleteGenre(id).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])(() => new _actions__WEBPACK_IMPORTED_MODULE_4__["DeleteGenreSuccess"](id)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])((err) => Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["of"])(new _actions__WEBPACK_IMPORTED_MODULE_4__["DeleteGenreFail"](`${new Date()}\n${JSON.stringify(err)}`))))));
    }
}
GenreEffect.ɵfac = function GenreEffect_Factory(t) { return new (t || GenreEffect)(_angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵinject"](_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Actions"]), _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵinject"](_services_genre_service__WEBPACK_IMPORTED_MODULE_6__["GenreService"])); };
GenreEffect.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdefineInjectable"]({ token: GenreEffect, factory: GenreEffect.ɵfac });
Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Effect"])()
], GenreEffect.prototype, "loadGenres$", void 0);
Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Effect"])()
], GenreEffect.prototype, "loadGenre$", void 0);
Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Effect"])()
], GenreEffect.prototype, "createGenre$", void 0);
Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Effect"])()
], GenreEffect.prototype, "updateGenre$", void 0);
Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Effect"])()
], GenreEffect.prototype, "deleteGenre$", void 0);


/***/ }),

/***/ "cyOE":
/*!*************************************************************!*\
  !*** ./src/app/store/comment-store/comment-store.module.ts ***!
  \*************************************************************/
/*! exports provided: CommentStoreModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CommentStoreModule", function() { return CommentStoreModule; });
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/common */ "ofXK");
/* harmony import */ var _ngrx_effects__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @ngrx/effects */ "9jGm");
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _effects__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./effects */ "fDmi");
/* harmony import */ var _reducer__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./reducer */ "t4RW");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/core */ "fXoL");








class CommentStoreModule {
}
CommentStoreModule.ɵmod = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdefineNgModule"]({ type: CommentStoreModule });
CommentStoreModule.ɵinj = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdefineInjector"]({ factory: function CommentStoreModule_Factory(t) { return new (t || CommentStoreModule)(); }, providers: [_effects__WEBPACK_IMPORTED_MODULE_3__["CommentEffect"]], imports: [[
            _angular_common__WEBPACK_IMPORTED_MODULE_0__["CommonModule"],
            _ngrx_store__WEBPACK_IMPORTED_MODULE_2__["StoreModule"].forFeature('comments', _reducer__WEBPACK_IMPORTED_MODULE_4__["commentReducer"]),
            _ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["EffectsModule"].forFeature([_effects__WEBPACK_IMPORTED_MODULE_3__["CommentEffect"]]),
        ]] });
(function () { (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵsetNgModuleScope"](CommentStoreModule, { imports: [_angular_common__WEBPACK_IMPORTED_MODULE_0__["CommonModule"], _ngrx_store__WEBPACK_IMPORTED_MODULE_2__["StoreFeatureModule"], _ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["EffectsFeatureModule"]] }); })();


/***/ }),

/***/ "d4cG":
/*!**********************************************!*\
  !*** ./src/app/store/genre-store/reducer.ts ***!
  \**********************************************/
/*! exports provided: genreReducer */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "genreReducer", function() { return genreReducer; });
/* harmony import */ var _actions__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./actions */ "H2Fn");
/* harmony import */ var _state__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./state */ "MpPM");


function genreReducer(state = _state__WEBPACK_IMPORTED_MODULE_1__["initialGenreState"], action) {
    switch (action.type) {
        case _actions__WEBPACK_IMPORTED_MODULE_0__["GenreActionTypes"].LOAD_GENRES_SUCCESS: {
            return _state__WEBPACK_IMPORTED_MODULE_1__["genreAdapter"].setAll(action.payload, Object.assign(Object.assign({}, state), { loading: false, loaded: true }));
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["GenreActionTypes"].LOAD_GENRES_FAIL: {
            return Object.assign(Object.assign({}, state), { entities: {}, loading: false, loaded: false, error: action.payload });
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["GenreActionTypes"].LOAD_GENRE_SUCCESS: {
            return _state__WEBPACK_IMPORTED_MODULE_1__["genreAdapter"].addOne(action.payload, Object.assign(Object.assign({}, state), { selectedGenreId: action.payload.id }));
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["GenreActionTypes"].LOAD_GENRE_FAIL: {
            return Object.assign(Object.assign({}, state), { error: action.payload });
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["GenreActionTypes"].CREATE_GENRE_SUCCESS: {
            return _state__WEBPACK_IMPORTED_MODULE_1__["genreAdapter"].addOne(action.payload, state);
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["GenreActionTypes"].CREATE_GENRE_FAIL: {
            return Object.assign(Object.assign({}, state), { error: action.payload });
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["GenreActionTypes"].UPDATE_GENRE_SUCCESS: {
            return _state__WEBPACK_IMPORTED_MODULE_1__["genreAdapter"].updateOne(action.payload, state);
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["GenreActionTypes"].UPDATE_GENRE_FAIL: {
            return Object.assign(Object.assign({}, state), { error: action.payload });
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["GenreActionTypes"].DELETE_GENRE_SUCCESS: {
            return _state__WEBPACK_IMPORTED_MODULE_1__["genreAdapter"].removeOne(action.payload, state);
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["GenreActionTypes"].DELETE_GENRE_FAIL: {
            return Object.assign(Object.assign({}, state), { error: action.payload });
        }
        default: {
            return state;
        }
    }
}


/***/ }),

/***/ "fDmi":
/*!************************************************!*\
  !*** ./src/app/store/comment-store/effects.ts ***!
  \************************************************/
/*! exports provided: CommentEffect */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CommentEffect", function() { return CommentEffect; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "mrSG");
/* harmony import */ var _ngrx_effects__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @ngrx/effects */ "9jGm");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs */ "qCKp");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs/operators */ "kU1M");
/* harmony import */ var _actions__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./actions */ "39v3");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _services_comment_service__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../../services/comment.service */ "mxDV");








class CommentEffect {
    constructor(actions$, commentService) {
        this.actions$ = actions$;
        this.commentService = commentService;
        this.loadComments$ = this.actions$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions__WEBPACK_IMPORTED_MODULE_4__["CommentActionTypes"].LOAD_COMMENTS), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])((action) => this.commentService.loadComments().pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((comments) => new _actions__WEBPACK_IMPORTED_MODULE_4__["LoadCommentsSuccess"](comments)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])((err) => Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["of"])(new _actions__WEBPACK_IMPORTED_MODULE_4__["LoadCommentsFail"](`${new Date()}\n${JSON.stringify(err)}`))))));
        this.loadComment$ = this.actions$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions__WEBPACK_IMPORTED_MODULE_4__["CommentActionTypes"].LOAD_COMMENT), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])((action) => this.commentService.loadCommentById(action.payload).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((comment) => new _actions__WEBPACK_IMPORTED_MODULE_4__["LoadCommentSuccess"](comment)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])((err) => Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["of"])(new _actions__WEBPACK_IMPORTED_MODULE_4__["LoadCommentFail"](`${new Date()}\n${JSON.stringify(err)}`))))));
        this.createComment$ = this.actions$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions__WEBPACK_IMPORTED_MODULE_4__["CommentActionTypes"].CREATE_COMMENT), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((action) => action.payload), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])((comment) => this.commentService.createComment(comment).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((newComment) => new _actions__WEBPACK_IMPORTED_MODULE_4__["CreateCommentSuccess"](newComment)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])((err) => Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["of"])(new _actions__WEBPACK_IMPORTED_MODULE_4__["CreateCommentFail"](`${new Date()}\n${JSON.stringify(err)}`))))));
        this.updateComment$ = this.actions$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions__WEBPACK_IMPORTED_MODULE_4__["CommentActionTypes"].UPDATE_COMMENT), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((action) => action.payload), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])((comment) => this.commentService.updateComment(comment).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((updateComment) => new _actions__WEBPACK_IMPORTED_MODULE_4__["UpdateCommentSuccess"]({
            id: updateComment.id,
            changes: updateComment,
        })), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])((err) => Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["of"])(new _actions__WEBPACK_IMPORTED_MODULE_4__["UpdateCommentFail"](`${new Date()}\n${JSON.stringify(err)}`))))));
        this.deleteComment$ = this.actions$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions__WEBPACK_IMPORTED_MODULE_4__["CommentActionTypes"].DELETE_COMMENT), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((action) => action.payload), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])((id) => this.commentService.deleteComment(id).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])(() => new _actions__WEBPACK_IMPORTED_MODULE_4__["DeleteCommentSuccess"](id)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])((err) => Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["of"])(new _actions__WEBPACK_IMPORTED_MODULE_4__["DeleteCommentFail"](`${new Date()}\n${JSON.stringify(err)}`))))));
    }
}
CommentEffect.ɵfac = function CommentEffect_Factory(t) { return new (t || CommentEffect)(_angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵinject"](_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Actions"]), _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵinject"](_services_comment_service__WEBPACK_IMPORTED_MODULE_6__["CommentService"])); };
CommentEffect.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdefineInjectable"]({ token: CommentEffect, factory: CommentEffect.ɵfac });
Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Effect"])()
], CommentEffect.prototype, "loadComments$", void 0);
Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Effect"])()
], CommentEffect.prototype, "loadComment$", void 0);
Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Effect"])()
], CommentEffect.prototype, "createComment$", void 0);
Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Effect"])()
], CommentEffect.prototype, "updateComment$", void 0);
Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Effect"])()
], CommentEffect.prototype, "deleteComment$", void 0);


/***/ }),

/***/ "gD7T":
/*!*************************************************************!*\
  !*** ./src/app/components/book-edit/book-edit.component.ts ***!
  \*************************************************************/
/*! exports provided: BookEditComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "BookEditComponent", function() { return BookEditComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/forms */ "3Pt+");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs */ "qCKp");
/* harmony import */ var _models_author_model__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../models/author.model */ "CfTE");
/* harmony import */ var _models_book_model__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../../models/book.model */ "+UQt");
/* harmony import */ var _models_genre_model__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../../models/genre.model */ "MUMk");
/* harmony import */ var primeng_dialog__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! primeng/dialog */ "/RsI");
/* harmony import */ var primeng_inputtext__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! primeng/inputtext */ "7kUa");
/* harmony import */ var primeng_tooltip__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! primeng/tooltip */ "xlun");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! @angular/common */ "ofXK");
/* harmony import */ var primeng_dropdown__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! primeng/dropdown */ "arFO");
/* harmony import */ var primeng_button__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! primeng/button */ "jIHw");














function BookEditComponent_div_7_div_1_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](0, "div");
} }
function BookEditComponent_div_7_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 15);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](1, BookEditComponent_div_7_div_1_Template, 1, 0, "div", 16);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r0 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx_r0.bookForm.controls["name"].errors.required);
} }
const _c0 = function () { return { "width": "100%" }; };
class BookEditComponent {
    constructor(formBuilder) {
        this.formBuilder = formBuilder;
        this.display = false;
        this.book = new _models_book_model__WEBPACK_IMPORTED_MODULE_4__["BookModel"]();
        this.authors = [];
        this.author = null;
        this.genres = [];
        this.genre = null;
        this.displayChange = new _angular_core__WEBPACK_IMPORTED_MODULE_0__["EventEmitter"]();
        this.bookChange = new _angular_core__WEBPACK_IMPORTED_MODULE_0__["EventEmitter"]();
        this.selectedAuthor = null;
        this.selectedGenre = null;
        this.bookForm = this.formBuilder.group({
            id: null,
            name: ['', _angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required],
        });
        this.dialogHeader = '';
        this.submitted = false;
        this.subscriptions = new rxjs__WEBPACK_IMPORTED_MODULE_2__["Subscription"]();
    }
    ngOnInit() {
    }
    ngOnDestroy() {
        this.subscriptions.unsubscribe();
    }
    onDisplayChange(val) {
        if (this.display) {
            if (this.book) {
                this.dialogHeader = 'Редактировать книгу';
                this.bookForm.patchValue({
                    id: this.book && this.book.id ? this.book.id : null,
                    name: this.book.name,
                });
            }
            else {
                this.dialogHeader = 'Добавить книгу';
                this.bookForm.patchValue({
                    id: null,
                    name: '',
                });
            }
        }
        this.submitted = false;
        this.display = val;
        this.displayChange.emit(val);
    }
    onBookChange() {
        this.submitted = true;
        const updateBook = {
            id: this.book.id,
            name: '',
            author: new _models_author_model__WEBPACK_IMPORTED_MODULE_3__["AuthorModel"],
            genres: [new _models_genre_model__WEBPACK_IMPORTED_MODULE_5__["GenreModel"]()],
        };
        if (this.bookForm.valid) {
            this.book = updateBook;
            this.bookChange.emit(updateBook);
            this.display = false;
        }
    }
    onAuthorChange(event) {
    }
    onGenreChange(event) {
    }
}
BookEditComponent.ɵfac = function BookEditComponent_Factory(t) { return new (t || BookEditComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_angular_forms__WEBPACK_IMPORTED_MODULE_1__["FormBuilder"])); };
BookEditComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: BookEditComponent, selectors: [["app-book-edit"]], inputs: { display: "display", book: "book", authors: "authors", author: "author", genres: "genres", genre: "genre" }, outputs: { displayChange: "displayChange", bookChange: "bookChange" }, decls: 21, vars: 15, consts: [["modal", "true", 3, "visible", "header", "visibleChange", "onShow", "onHide"], [1, "dialog"], [1, "form-inline", 3, "formGroup", "submit"], ["for", "name", 1, "label"], [1, "ui-inputgroup"], ["pInputText", "", "type", "text", "id", "name", "formControlName", "name", "placeholder", "\u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435 \u043A\u043D\u0438\u0433\u0438", "pTooltip", "\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u043D\u0430\u0437\u0432\u0430\u043D\u0438\u0435 \u043A\u043D\u0438\u0433\u0438", 1, "input-text"], ["class", "alert alert-danger", 4, "ngIf"], ["for", "authors", 1, "label"], [1, "dropdown"], ["id", "authors", "placeholder", "\u0412\u044B\u0431\u0435\u0440\u0438\u0442\u0435 \u0430\u0432\u0442\u043E\u0440\u0430", "optionLabel", "name", 3, "options", "ngModel", "ngModelChange"], ["for", "genre", 1, "label"], ["id", "genre", "placeholder", "\u0412\u044B\u0431\u0435\u0440\u0438\u0442\u0435 \u0436\u0430\u043D\u0440", "optionLabel", "name", 3, "options", "ngModel", "ngModelChange"], [1, "btn-blk"], ["type", "submit", "label", "\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C", "icon", "pi pi-check", "iconPos", "right", 1, "button", 3, "disabled"], ["type", "button", "label", "\u041E\u0442\u043C\u0435\u043D\u0430", "icon", "pi pi-times", "iconPos", "right", 1, "button", 3, "onClick"], [1, "alert", "alert-danger"], [4, "ngIf"]], template: function BookEditComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "p-dialog", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("visibleChange", function BookEditComponent_Template_p_dialog_visibleChange_0_listener($event) { return ctx.display = $event; })("onShow", function BookEditComponent_Template_p_dialog_onShow_0_listener() { return ctx.onDisplayChange(true); })("onHide", function BookEditComponent_Template_p_dialog_onHide_0_listener() { return ctx.onDisplayChange(false); });
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](2, "form", 2);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("submit", function BookEditComponent_Template_form_submit_2_listener() { return ctx.onBookChange(); });
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](3, "label", 3);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](4, "\u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435 \u043A\u043D\u0438\u0433\u0438 *");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](5, "div", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](6, "input", 5);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](7, BookEditComponent_div_7_Template, 2, 1, "div", 6);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](8, "label", 7);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](9, "\u0410\u0432\u0442\u043E\u0440");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](10, "div", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](11, "div", 8);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](12, "p-dropdown", 9);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("ngModelChange", function BookEditComponent_Template_p_dropdown_ngModelChange_12_listener($event) { return ctx.selectedAuthor = $event; })("ngModelChange", function BookEditComponent_Template_p_dropdown_ngModelChange_12_listener($event) { return ctx.onAuthorChange($event); });
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](13, "label", 10);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](14, "\u0416\u0430\u043D\u0440");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](15, "div", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](16, "div", 8);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](17, "p-dropdown", 11);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("ngModelChange", function BookEditComponent_Template_p_dropdown_ngModelChange_17_listener($event) { return ctx.selectedGenre = $event; })("ngModelChange", function BookEditComponent_Template_p_dropdown_ngModelChange_17_listener($event) { return ctx.onGenreChange($event); });
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](18, "div", 12);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](19, "p-button", 13);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](20, "p-button", 14);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("onClick", function BookEditComponent_Template_p_button_onClick_20_listener() { return ctx.onDisplayChange(false); });
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵpropertyInterpolate"]("header", ctx.dialogHeader);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("visible", ctx.display);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("formGroup", ctx.bookForm);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](5);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx.submitted && ctx.bookForm.controls["name"].invalid && (ctx.bookForm.controls["name"].dirty || ctx.bookForm.controls["name"].touched));
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](5);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵstyleMap"](_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵpureFunction0"](13, _c0));
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("options", ctx.authors)("ngModel", ctx.selectedAuthor);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](5);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵstyleMap"](_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵpureFunction0"](14, _c0));
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("options", ctx.genres)("ngModel", ctx.selectedGenre);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("disabled", !ctx.bookForm.valid);
    } }, directives: [primeng_dialog__WEBPACK_IMPORTED_MODULE_6__["Dialog"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["ɵangular_packages_forms_forms_y"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["NgControlStatusGroup"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["FormGroupDirective"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["DefaultValueAccessor"], primeng_inputtext__WEBPACK_IMPORTED_MODULE_7__["InputText"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["NgControlStatus"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["FormControlName"], primeng_tooltip__WEBPACK_IMPORTED_MODULE_8__["Tooltip"], _angular_common__WEBPACK_IMPORTED_MODULE_9__["NgIf"], primeng_dropdown__WEBPACK_IMPORTED_MODULE_10__["Dropdown"], _angular_forms__WEBPACK_IMPORTED_MODULE_1__["NgModel"], primeng_button__WEBPACK_IMPORTED_MODULE_11__["Button"]], styles: [".dialog[_ngcontent-%COMP%] {\r\n  width: 500px;\r\n}\r\n.ui-inputgroup[_ngcontent-%COMP%] {\r\n  width: 100%\r\n}\r\n.input-text[_ngcontent-%COMP%] {\r\n  width: 100%\r\n}\r\n.button[_ngcontent-%COMP%] {\r\n  padding-top: 15px;\r\n  width: 150px;\r\n}\r\n.label[_ngcontent-%COMP%] {\r\n  font-weight: bold;\r\n  padding-top: 10px;\r\n}\r\n.btn-blk[_ngcontent-%COMP%] {\r\n  display: flex;\r\n  flex-direction: row;\r\n  justify-content: flex-start;\r\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImJvb2stZWRpdC5jb21wb25lbnQuY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0VBQ0UsWUFBWTtBQUNkO0FBQ0E7RUFDRTtBQUNGO0FBQ0E7RUFDRTtBQUNGO0FBQ0E7RUFDRSxpQkFBaUI7RUFDakIsWUFBWTtBQUNkO0FBQ0E7RUFDRSxpQkFBaUI7RUFDakIsaUJBQWlCO0FBQ25CO0FBQ0E7RUFDRSxhQUFhO0VBQ2IsbUJBQW1CO0VBQ25CLDJCQUEyQjtBQUM3QiIsImZpbGUiOiJib29rLWVkaXQuY29tcG9uZW50LmNzcyIsInNvdXJjZXNDb250ZW50IjpbIi5kaWFsb2cge1xyXG4gIHdpZHRoOiA1MDBweDtcclxufVxyXG4udWktaW5wdXRncm91cCB7XHJcbiAgd2lkdGg6IDEwMCVcclxufVxyXG4uaW5wdXQtdGV4dCB7XHJcbiAgd2lkdGg6IDEwMCVcclxufVxyXG4uYnV0dG9uIHtcclxuICBwYWRkaW5nLXRvcDogMTVweDtcclxuICB3aWR0aDogMTUwcHg7XHJcbn1cclxuLmxhYmVsIHtcclxuICBmb250LXdlaWdodDogYm9sZDtcclxuICBwYWRkaW5nLXRvcDogMTBweDtcclxufVxyXG4uYnRuLWJsayB7XHJcbiAgZGlzcGxheTogZmxleDtcclxuICBmbGV4LWRpcmVjdGlvbjogcm93O1xyXG4gIGp1c3RpZnktY29udGVudDogZmxleC1zdGFydDtcclxufVxyXG4iXX0= */"] });


/***/ }),

/***/ "m1kY":
/*!************************************************!*\
  !*** ./src/app/store/genre-store/selectors.ts ***!
  \************************************************/
/*! exports provided: getGenres, getGenresLoading, getGenresLoaded, getError, getCurrentGenreId */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getGenres", function() { return getGenres; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getGenresLoading", function() { return getGenresLoading; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getGenresLoaded", function() { return getGenresLoaded; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getError", function() { return getError; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getCurrentGenreId", function() { return getCurrentGenreId; });
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _state__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./state */ "MpPM");


const getGenreFeatureState = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createFeatureSelector"])('genres');
const getGenres = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createSelector"])(getGenreFeatureState, _state__WEBPACK_IMPORTED_MODULE_1__["genreAdapter"].getSelectors().selectAll);
const getGenresLoading = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createSelector"])(getGenreFeatureState, (state) => state.loading);
const getGenresLoaded = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createSelector"])(getGenreFeatureState, (state) => state.loaded);
const getError = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createSelector"])(getGenreFeatureState, (state) => state.error);
const getCurrentGenreId = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createSelector"])(getGenreFeatureState, (state) => state.selectedGenreId);
// export const getCurrentGenre = createSelector(
//   getGenreFeatureState,
//   getCurrentGenreId,
//   (state) => state.entities[state.selectedGenreId]
// );


/***/ }),

/***/ "mxDV":
/*!*********************************************!*\
  !*** ./src/app/services/comment.service.ts ***!
  \*********************************************/
/*! exports provided: CommentService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CommentService", function() { return CommentService; });
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! rxjs/operators */ "kU1M");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "tk/3");



class CommentService {
    constructor(http) {
        this.http = http;
    }
    loadComments() {
        return this.http
            .get('/api/comment')
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_0__["map"])((data) => data));
    }
    loadCommentById(payload) {
        return this.http
            .get(`/api/comment/${payload}`)
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_0__["map"])((data) => data));
    }
    createComment(payload) {
        return this.http
            .post('/api/comment', payload)
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_0__["map"])((data) => data));
    }
    updateComment(payload) {
        return this.http
            .put(`/api/comment/${payload.id}`, payload)
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_0__["map"])((data) => data));
    }
    deleteComment(payload) {
        return this.http
            .delete(`/api/comment/${payload}`)
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_0__["map"])((data) => data));
    }
}
CommentService.ɵfac = function CommentService_Factory(t) { return new (t || CommentService)(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵinject"](_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"])); };
CommentService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineInjectable"]({ token: CommentService, factory: CommentService.ɵfac });


/***/ }),

/***/ "pXY/":
/*!*****************************************************************!*\
  !*** ./src/app/components/authors-tab/authors-tab.component.ts ***!
  \*****************************************************************/
/*! exports provided: AuthorsTabComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AuthorsTabComponent", function() { return AuthorsTabComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");

class AuthorsTabComponent {
    constructor() { }
    ngOnInit() {
    }
}
AuthorsTabComponent.ɵfac = function AuthorsTabComponent_Factory(t) { return new (t || AuthorsTabComponent)(); };
AuthorsTabComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: AuthorsTabComponent, selectors: [["app-authors-tab"]], decls: 6, vars: 0, consts: [[1, "label"]], template: function AuthorsTabComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "p");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1, "authors-tab works!");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](2, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](3, "p");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](4, "b");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](5, "\u0410\u0432\u0442\u043E\u0440\u044B");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    } }, styles: ["\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJhdXRob3JzLXRhYi5jb21wb25lbnQuY3NzIn0= */"] });


/***/ }),

/***/ "qd8X":
/*!*********************************************!*\
  !*** ./src/app/store/book-store/reducer.ts ***!
  \*********************************************/
/*! exports provided: bookReducer */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "bookReducer", function() { return bookReducer; });
/* harmony import */ var _actions__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./actions */ "Cgsz");
/* harmony import */ var _state__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./state */ "Cd6r");


function bookReducer(state = _state__WEBPACK_IMPORTED_MODULE_1__["initialBookState"], action) {
    switch (action.type) {
        case _actions__WEBPACK_IMPORTED_MODULE_0__["BookActionTypes"].LOAD_BOOKS_SUCCESS: {
            return _state__WEBPACK_IMPORTED_MODULE_1__["bookAdapter"].setAll(action.payload, Object.assign(Object.assign({}, state), { loading: false, loaded: true }));
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["BookActionTypes"].LOAD_BOOKS_FAIL: {
            return Object.assign(Object.assign({}, state), { entities: {}, loading: false, loaded: false, error: action.payload });
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["BookActionTypes"].LOAD_BOOK_SUCCESS: {
            return _state__WEBPACK_IMPORTED_MODULE_1__["bookAdapter"].addOne(action.payload, Object.assign(Object.assign({}, state), { selectedBookId: action.payload.id }));
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["BookActionTypes"].LOAD_BOOK_FAIL: {
            return Object.assign(Object.assign({}, state), { error: action.payload });
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["BookActionTypes"].CREATE_BOOK_SUCCESS: {
            return _state__WEBPACK_IMPORTED_MODULE_1__["bookAdapter"].addOne(action.payload, state);
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["BookActionTypes"].CREATE_BOOK_FAIL: {
            return Object.assign(Object.assign({}, state), { error: action.payload });
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["BookActionTypes"].UPDATE_BOOK_SUCCESS: {
            return _state__WEBPACK_IMPORTED_MODULE_1__["bookAdapter"].updateOne(action.payload, state);
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["BookActionTypes"].UPDATE_BOOK_FAIL: {
            return Object.assign(Object.assign({}, state), { error: action.payload });
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["BookActionTypes"].DELETE_BOOK_SUCCESS: {
            return _state__WEBPACK_IMPORTED_MODULE_1__["bookAdapter"].removeOne(action.payload, state);
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["BookActionTypes"].DELETE_BOOK_FAIL: {
            return Object.assign(Object.assign({}, state), { error: action.payload });
        }
        default: {
            return state;
        }
    }
}


/***/ }),

/***/ "t4RW":
/*!************************************************!*\
  !*** ./src/app/store/comment-store/reducer.ts ***!
  \************************************************/
/*! exports provided: commentReducer */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "commentReducer", function() { return commentReducer; });
/* harmony import */ var _actions__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./actions */ "39v3");
/* harmony import */ var _state__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./state */ "JsJV");


function commentReducer(state = _state__WEBPACK_IMPORTED_MODULE_1__["initialCommentState"], action) {
    switch (action.type) {
        case _actions__WEBPACK_IMPORTED_MODULE_0__["CommentActionTypes"].LOAD_COMMENTS_SUCCESS: {
            return _state__WEBPACK_IMPORTED_MODULE_1__["commentAdapter"].setAll(action.payload, Object.assign(Object.assign({}, state), { loading: false, loaded: true }));
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["CommentActionTypes"].LOAD_COMMENTS_FAIL: {
            return Object.assign(Object.assign({}, state), { entities: {}, loading: false, loaded: false, error: action.payload });
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["CommentActionTypes"].LOAD_COMMENT_SUCCESS: {
            return _state__WEBPACK_IMPORTED_MODULE_1__["commentAdapter"].addOne(action.payload, Object.assign(Object.assign({}, state), { selectedCommentId: action.payload.id }));
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["CommentActionTypes"].LOAD_COMMENT_FAIL: {
            return Object.assign(Object.assign({}, state), { error: action.payload });
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["CommentActionTypes"].CREATE_COMMENT_SUCCESS: {
            return _state__WEBPACK_IMPORTED_MODULE_1__["commentAdapter"].addOne(action.payload, state);
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["CommentActionTypes"].CREATE_COMMENT_FAIL: {
            return Object.assign(Object.assign({}, state), { error: action.payload });
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["CommentActionTypes"].UPDATE_COMMENT_SUCCESS: {
            return _state__WEBPACK_IMPORTED_MODULE_1__["commentAdapter"].updateOne(action.payload, state);
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["CommentActionTypes"].UPDATE_COMMENT_FAIL: {
            return Object.assign(Object.assign({}, state), { error: action.payload });
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["CommentActionTypes"].DELETE_COMMENT_SUCCESS: {
            return _state__WEBPACK_IMPORTED_MODULE_1__["commentAdapter"].removeOne(action.payload, state);
        }
        case _actions__WEBPACK_IMPORTED_MODULE_0__["CommentActionTypes"].DELETE_COMMENT_FAIL: {
            return Object.assign(Object.assign({}, state), { error: action.payload });
        }
        default: {
            return state;
        }
    }
}


/***/ }),

/***/ "vY5A":
/*!***************************************!*\
  !*** ./src/app/app-routing.module.ts ***!
  \***************************************/
/*! exports provided: AppRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppRoutingModule", function() { return AppRoutingModule; });
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/router */ "tyNb");
/* harmony import */ var _components_books_view_books_view_component__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./components/books-view/books-view.component */ "x34F");
/* harmony import */ var _components_home_page_home_page_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./components/home-page/home-page.component */ "atC9");
/* harmony import */ var _components_manage_page_manage_page_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./components/manage-page/manage-page.component */ "afxI");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/core */ "fXoL");






const routes = [
    { path: '', component: _components_home_page_home_page_component__WEBPACK_IMPORTED_MODULE_2__["HomePageComponent"] },
    { path: 'books-view', component: _components_books_view_books_view_component__WEBPACK_IMPORTED_MODULE_1__["BooksViewComponent"] },
    { path: 'manage', component: _components_manage_page_manage_page_component__WEBPACK_IMPORTED_MODULE_3__["ManagePageComponent"] },
];
class AppRoutingModule {
}
AppRoutingModule.ɵmod = _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵdefineNgModule"]({ type: AppRoutingModule });
AppRoutingModule.ɵinj = _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵdefineInjector"]({ factory: function AppRoutingModule_Factory(t) { return new (t || AppRoutingModule)(); }, imports: [[_angular_router__WEBPACK_IMPORTED_MODULE_0__["RouterModule"].forRoot(routes, { relativeLinkResolution: 'legacy' })], _angular_router__WEBPACK_IMPORTED_MODULE_0__["RouterModule"]] });
(function () { (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵsetNgModuleScope"](AppRoutingModule, { imports: [_angular_router__WEBPACK_IMPORTED_MODULE_0__["RouterModule"]], exports: [_angular_router__WEBPACK_IMPORTED_MODULE_0__["RouterModule"]] }); })();


/***/ }),

/***/ "w8RM":
/*!*******************************************!*\
  !*** ./src/app/store/book-store/index.ts ***!
  \*******************************************/
/*! exports provided: BookStoreModule, BookStoreActions, BookStoreSelectors, BookStoreState */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _actions__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./actions */ "Cgsz");
/* harmony reexport (module object) */ __webpack_require__.d(__webpack_exports__, "BookStoreActions", function() { return _actions__WEBPACK_IMPORTED_MODULE_0__; });
/* harmony import */ var _selectors__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./selectors */ "IspA");
/* harmony reexport (module object) */ __webpack_require__.d(__webpack_exports__, "BookStoreSelectors", function() { return _selectors__WEBPACK_IMPORTED_MODULE_1__; });
/* harmony import */ var _state__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./state */ "Cd6r");
/* harmony reexport (module object) */ __webpack_require__.d(__webpack_exports__, "BookStoreState", function() { return _state__WEBPACK_IMPORTED_MODULE_2__; });
/* harmony import */ var _book_store_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./book-store.module */ "/AxE");
/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "BookStoreModule", function() { return _book_store_module__WEBPACK_IMPORTED_MODULE_3__["BookStoreModule"]; });








/***/ }),

/***/ "whPw":
/*!***********************************************!*\
  !*** ./src/app/store/author-store/effects.ts ***!
  \***********************************************/
/*! exports provided: AuthorEffect */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AuthorEffect", function() { return AuthorEffect; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "mrSG");
/* harmony import */ var _ngrx_effects__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @ngrx/effects */ "9jGm");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs */ "qCKp");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs/operators */ "kU1M");
/* harmony import */ var _actions__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./actions */ "J/aB");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _services_author_service__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../../services/author.service */ "N+9M");








class AuthorEffect {
    constructor(actions$, authorService) {
        this.actions$ = actions$;
        this.authorService = authorService;
        this.loadAuthors$ = this.actions$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions__WEBPACK_IMPORTED_MODULE_4__["AuthorActionTypes"].LOAD_AUTHORS), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])((action) => this.authorService.loadAuthors().pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((authors) => new _actions__WEBPACK_IMPORTED_MODULE_4__["LoadAuthorsSuccess"](authors)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])((err) => Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["of"])(new _actions__WEBPACK_IMPORTED_MODULE_4__["LoadAuthorsFail"](`${new Date()}\n${JSON.stringify(err)}`))))));
        this.loadAuthor$ = this.actions$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions__WEBPACK_IMPORTED_MODULE_4__["AuthorActionTypes"].LOAD_AUTHOR), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])((action) => this.authorService.loadAuthorById(action.payload).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((author) => new _actions__WEBPACK_IMPORTED_MODULE_4__["LoadAuthorSuccess"](author)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])((err) => Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["of"])(new _actions__WEBPACK_IMPORTED_MODULE_4__["LoadAuthorFail"](`${new Date()}\n${JSON.stringify(err)}`))))));
        this.createAuthor$ = this.actions$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions__WEBPACK_IMPORTED_MODULE_4__["AuthorActionTypes"].CREATE_AUTHOR), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((action) => action.payload), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])((author) => this.authorService.createAuthor(author).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((newAuthor) => new _actions__WEBPACK_IMPORTED_MODULE_4__["CreateAuthorSuccess"](newAuthor)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])((err) => Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["of"])(new _actions__WEBPACK_IMPORTED_MODULE_4__["CreateAuthorFail"](`${new Date()}\n${JSON.stringify(err)}`))))));
        this.updateAuthor$ = this.actions$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions__WEBPACK_IMPORTED_MODULE_4__["AuthorActionTypes"].UPDATE_AUTHOR), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((action) => action.payload), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])((author) => this.authorService.updateAuthor(author).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((updateAuthor) => new _actions__WEBPACK_IMPORTED_MODULE_4__["UpdateAuthorSuccess"]({
            id: updateAuthor.id,
            changes: updateAuthor,
        })), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])((err) => Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["of"])(new _actions__WEBPACK_IMPORTED_MODULE_4__["UpdateAuthorFail"](`${new Date()}\n${JSON.stringify(err)}`))))));
        this.deleteAuthor$ = this.actions$.pipe(Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["ofType"])(_actions__WEBPACK_IMPORTED_MODULE_4__["AuthorActionTypes"].DELETE_AUTHOR), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])((action) => action.payload), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["mergeMap"])((id) => this.authorService.deleteAuthor(id).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])(() => new _actions__WEBPACK_IMPORTED_MODULE_4__["DeleteAuthorSuccess"](id)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])((err) => Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["of"])(new _actions__WEBPACK_IMPORTED_MODULE_4__["DeleteAuthorFail"](`${new Date()}\n${JSON.stringify(err)}`))))));
    }
}
AuthorEffect.ɵfac = function AuthorEffect_Factory(t) { return new (t || AuthorEffect)(_angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵinject"](_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Actions"]), _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵinject"](_services_author_service__WEBPACK_IMPORTED_MODULE_6__["AuthorService"])); };
AuthorEffect.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdefineInjectable"]({ token: AuthorEffect, factory: AuthorEffect.ɵfac });
Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Effect"])()
], AuthorEffect.prototype, "loadAuthors$", void 0);
Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Effect"])()
], AuthorEffect.prototype, "loadAuthor$", void 0);
Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Effect"])()
], AuthorEffect.prototype, "createAuthor$", void 0);
Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Effect"])()
], AuthorEffect.prototype, "updateAuthor$", void 0);
Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_ngrx_effects__WEBPACK_IMPORTED_MODULE_1__["Effect"])()
], AuthorEffect.prototype, "deleteAuthor$", void 0);


/***/ }),

/***/ "x34F":
/*!***************************************************************!*\
  !*** ./src/app/components/books-view/books-view.component.ts ***!
  \***************************************************************/
/*! exports provided: BooksViewComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "BooksViewComponent", function() { return BooksViewComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");

class BooksViewComponent {
    constructor() { }
    ngOnInit() {
    }
}
BooksViewComponent.ɵfac = function BooksViewComponent_Factory(t) { return new (t || BooksViewComponent)(); };
BooksViewComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: BooksViewComponent, selectors: [["app-books-view"]], decls: 2, vars: 0, template: function BooksViewComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "p");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1, "books-view works!");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    } }, styles: ["\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJib29rcy12aWV3LmNvbXBvbmVudC5jc3MifQ== */"] });


/***/ }),

/***/ "y3Pi":
/*!********************************************!*\
  !*** ./src/app/store/genre-store/index.ts ***!
  \********************************************/
/*! exports provided: GenreStoreModule, GenreStoreActions, GenreStoreSelectors, GenreStoreState */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _actions__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./actions */ "H2Fn");
/* harmony reexport (module object) */ __webpack_require__.d(__webpack_exports__, "GenreStoreActions", function() { return _actions__WEBPACK_IMPORTED_MODULE_0__; });
/* harmony import */ var _selectors__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./selectors */ "m1kY");
/* harmony reexport (module object) */ __webpack_require__.d(__webpack_exports__, "GenreStoreSelectors", function() { return _selectors__WEBPACK_IMPORTED_MODULE_1__; });
/* harmony import */ var _state__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./state */ "MpPM");
/* harmony reexport (module object) */ __webpack_require__.d(__webpack_exports__, "GenreStoreState", function() { return _state__WEBPACK_IMPORTED_MODULE_2__; });
/* harmony import */ var _genre_store_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./genre-store.module */ "GE9g");
/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "GenreStoreModule", function() { return _genre_store_module__WEBPACK_IMPORTED_MODULE_3__["GenreStoreModule"]; });








/***/ }),

/***/ "zUnb":
/*!*********************!*\
  !*** ./src/main.ts ***!
  \*********************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/platform-browser */ "jhN1");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _app_app_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./app/app.module */ "ZAI4");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./environments/environment */ "AytR");




if (_environments_environment__WEBPACK_IMPORTED_MODULE_3__["environment"].production) {
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["enableProdMode"])();
}
_angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__["platformBrowser"]().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_2__["AppModule"])
    .catch(err => console.error(err));


/***/ }),

/***/ "zn8P":
/*!******************************************************!*\
  !*** ./$$_lazy_route_resource lazy namespace object ***!
  \******************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncaught exception popping up in devtools
	return Promise.resolve().then(function() {
		var e = new Error("Cannot find module '" + req + "'");
		e.code = 'MODULE_NOT_FOUND';
		throw e;
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "zn8P";

/***/ })

},[[0,"runtime","vendor"]]]);
//# sourceMappingURL=main.js.map