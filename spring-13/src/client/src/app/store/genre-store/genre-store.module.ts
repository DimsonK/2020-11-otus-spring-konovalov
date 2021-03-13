import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { EffectsModule } from '@ngrx/effects';
import { StoreModule } from '@ngrx/store';
import { GenreEffect } from './effects';
import { genreReducer } from './reducer';

@NgModule({
  imports: [
    CommonModule,
    StoreModule.forFeature('genres', genreReducer),
    EffectsModule.forFeature([GenreEffect]),
  ],
  providers: [GenreEffect],
})
export class GenreStoreModule {
}
