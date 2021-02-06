import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AccordionModule } from 'primeng/accordion';
import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { CheckboxModule } from 'primeng/checkbox';
import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';
import { DynamicDialogModule } from 'primeng/dynamicdialog';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { ListboxModule } from 'primeng/listbox';
import { MenuModule } from 'primeng/menu';
import { MessageModule } from 'primeng/message';
import { MessagesModule } from 'primeng/messages';
import { PanelModule } from 'primeng/panel';
import { PanelMenuModule } from 'primeng/panelmenu';
import { RippleModule } from 'primeng/ripple';
import { TableModule } from 'primeng/table';
import { TabViewModule } from 'primeng/tabview';
import { ToastModule } from 'primeng/toast';
import { ToolbarModule } from 'primeng/toolbar';
import { TooltipModule } from 'primeng/tooltip';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthorEditComponent } from './components/author-edit/author-edit.component';
import { AuthorsTabComponent } from './components/authors-tab/authors-tab.component';
import { BookEditComponent } from './components/book-edit/book-edit.component';
import { BooksTabComponent } from './components/books-tab/books-tab.component';
import { BooksViewComponent } from './components/books-view/books-view.component';
import { CommentEditComponent } from './components/comment-edit/comment-edit.component';
import { CommentsTabComponent } from './components/comments-tab/comments-tab.component';
import { FooterComponent } from './components/footer/footer.component';
import { GenreEditComponent } from './components/genre-edit/genre-edit.component';
import { GenresTabComponent } from './components/genres-tab/genres-tab.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { ManagePageComponent } from './components/manage-page/manage-page.component';
import { AuthorService } from './services/author.service';
import { BookService } from './services/book.service';
import { CommentService } from './services/comment.service';
import { GenreService } from './services/genre.service';
import { RootStoreModule } from './store/root-store.module';

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AppRoutingModule,
    RootStoreModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    //  PrimeNG modules
    AccordionModule,
    ButtonModule,
    CardModule,
    CheckboxModule,
    DialogModule,
    DropdownModule,
    DynamicDialogModule,
    InputTextModule,
    InputTextareaModule,
    ListboxModule,
    MenuModule,
    PanelMenuModule,
    PanelModule,
    TabViewModule,
    TableModule,
    ToastModule,
    ToolbarModule,
    TooltipModule,
    RippleModule,
    MessagesModule,
    MessageModule
  ],
  declarations: [
    AppComponent,
    HomePageComponent,
    BookEditComponent,
    AuthorEditComponent,
    GenreEditComponent,
    CommentEditComponent,
    FooterComponent,
    ManagePageComponent,
    BooksViewComponent,
    AuthorsTabComponent,
    BooksTabComponent,
    CommentsTabComponent,
    GenresTabComponent,
  ],
  providers: [
    AuthorService,
    BookService,
    CommentService,
    GenreService,
    MessageService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
