import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home';
import { EntryComponent } from './entry';
import { LoginComponent } from './login';
import { RegisterComponent } from './register';
import { AuthGuard } from './_guards';

const appRoutes: Routes = [
    // { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  { path: '', component: EntryComponent, canActivate: [ AuthGuard ] },
  { path: 'entry', component: EntryComponent, canActivate: [ AuthGuard ] },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },

    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);
