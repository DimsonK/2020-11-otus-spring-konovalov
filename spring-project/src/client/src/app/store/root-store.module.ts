import { NgModule } from '@angular/core';
import { EffectsModule } from '@ngrx/effects';
import { StoreModule } from '@ngrx/store';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { environment } from '../../environments/environment';
import { AuthorStoreModule } from './author-store';
import { BookStoreModule } from './book-store';
import { CommentStoreModule } from './comment-store';
import { CurrentUserStoreModule } from './current-user-store';
import { GenreStoreModule } from './genre-store';

@NgModule({
  imports: [
    AuthorStoreModule,
    BookStoreModule,
    CommentStoreModule,
    GenreStoreModule,
    CurrentUserStoreModule,
    StoreModule.forRoot(
      {},
      {
        runtimeChecks: {
          strictStateImmutability: true,
          strictActionImmutability: true,
          strictStateSerializability: true,
          strictActionSerializability: true,
        },
      }
    ),
    EffectsModule.forRoot([]),
    StoreDevtoolsModule.instrument({
      maxAge: 25, // Retains last 25 states
      logOnly: environment.production, // Restrict extension to logs-only mode
    }),
  ],
  declarations: [],
})
export class RootStoreModule {
}
