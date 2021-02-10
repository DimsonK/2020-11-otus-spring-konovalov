import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BooksViewComponent } from './components/books-view/books-view.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { ManagePageComponent } from './components/manage-page/manage-page.component';

const routes: Routes = [
  {path: '', component: HomePageComponent},
  {path: 'books-view', component: BooksViewComponent, data: {searchText: ''}},
  {path: 'manage', component: ManagePageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
