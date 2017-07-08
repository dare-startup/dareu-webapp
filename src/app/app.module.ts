//frameworks
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, isDevMode  } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { Logger } from "angular2-logger/core";
import { environment }    from '../environments/environment';
import { HttpModule, Http, Response } from '@angular/http';
//*********************************************************************************************************************
//components
import { AppComponent } from './app.component';
import { WelcomeSectionComponent } from './component/home/welcome-section/welcome-section.component';
import { DareSectionComponent } from './component/home/dare-section/dare-section.component';
import { MechanismSectionComponent } from './component/home/mechanism-section/mechanism-section.component';
import { DevsSectionComponent } from './component/home/devs-section/devs-section.component';
import { AnnonymousNavbarComponent } from './component/navbar/annonymous-navbar/annonymous-navbar.component';
import { SigninComponent } from './component/signin/signin.component';
import { SignupComponent } from './component/signup/signup.component';
import { SigninCardComponent } from './component/signin/signin-card/signin-card.component';
import { ContactComponent } from './component/contact/contact.component';
import { NotFoundComponent } from './component/shared/not-found/not-found.component';
import { UserHomeComponent } from './component/user/home/user-home/user-home.component';
import { UserComponent } from './component/user/user.component';
import { DiscoverUsersComponent } from './component/user/discover/discover-users/discover-users.component';
import { DiscoverDaresComponent } from './component/user/discover/discover-dares/discover-dares.component';
import { UserNavbarComponent } from './component/navbar/user-navbar/user-navbar.component';
import { HotComponent } from './component/user/hot/hot.component';
import { FavoriteComponent } from './component/user/favorite/favorite.component';
import { SigninFormComponent } from './component/signin/signin-form/signin-form.component';
import { ContactFormComponent } from './component/contact/contact-form/contact-form.component';
import { SignupFormComponent } from './component/signup/signup-form/signup-form.component';
import { UsersGridComponent } from './component/user/discover/discover-users/users-grid/users-grid.component';
import { NewDareComponent } from './component/user/dare/new-dare/new-dare.component';
import { UserCardComponent } from './component/user/discover/discover-users/user-card/user-card.component';
import { UserProfileComponent } from './component/user/user-profile/user-profile.component';
import { DaresGridComponent } from './component/user/discover/discover-dares/dares-grid/dares-grid.component';
import { AdminNavbarComponent } from './component/navbar/admin-navbar/admin-navbar.component';
import { AdminHomeComponent } from './component/admin/admin-home/admin-home.component';
import { AdminDaresComponent } from './component/admin/admin-dares/admin-dares.component';
import { AdminUsersComponent } from './component/admin/admin-users/admin-users.component';
import { AdminSponsorsComponent } from './component/admin/admin-sponsors/admin-sponsors.component';
import { AdminConfigComponent } from './component/admin/admin-config/admin-config.component';
//*********************************************************************************************************************
//providers
import { AuthenticationService } from './service/security/authentication.service';
import { DareuPublicBackendService } from './service/public/dareu-public-backend.service';
import { AuthGuard } from './guard/auth-guard.guard';
import { DareuUserBackendService } from './service/user/dareu-user-backend.service';
import { PaginationComponent } from './component/utilities/pagination/pagination.component';
import { PairsPipe } from './pipe/pairs.pipe';
import { AdminBackendService } from './service/admin/admin-backend.service';
import { ServerUtilities } from './service/server-utilities';
import { AdminCategoriesComponent } from './component/admin/admin-config/admin-categories/admin-categories.component';
//*********************************************************************************************************************



//application routes
const applicationRoutes: Routes = [
  //annonymous paths
  { path: '', component: WelcomeSectionComponent },
  { path: 'signin', component: SigninComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'contact', component: ContactComponent },

  //user paths
  { path: 'user', canActivate: [AuthGuard],
    children: [
      { path: '', component: UserComponent },
      { path: 'discover',
        children: [
          { path: "users", component: DiscoverUsersComponent },
          { path: "dares", component: DiscoverDaresComponent }
        ]
      },
      //dare paths
      { path: 'dare', canActivate: [AuthGuard],
        children: [
          { path: 'create', component: NewDareComponent }
        ]
      },
      { path: ':profile/:id', component: UserProfileComponent},
      { path: 'hot', component: HotComponent },
      { path: 'favorites', component: FavoriteComponent }
    ]
  },
  { path: 'admin', canActivate: [AuthGuard],
    children: [
      { path: '', component: AdminHomeComponent },
      { path: 'dares', component: AdminDaresComponent },
      { path: 'users', component: AdminUsersComponent },
      { path: 'sponsors', component: AdminSponsorsComponent },
      { path: 'config', component: AdminConfigComponent },
    ]
  },
  //default paths
  { path: '**', component: NotFoundComponent }
];

//module declaration
@NgModule({
  declarations: [
    AppComponent,
    WelcomeSectionComponent,
    DareSectionComponent,
    MechanismSectionComponent,
    DevsSectionComponent,
    AnnonymousNavbarComponent,
    SigninComponent,
    SignupComponent,
    SigninCardComponent,
    ContactComponent,
    NotFoundComponent,
    UserHomeComponent,
    UserComponent,
    UserNavbarComponent,
    DiscoverDaresComponent,
    DiscoverUsersComponent,
    HotComponent,
    FavoriteComponent,
    SigninFormComponent,
    ContactFormComponent,
    SignupFormComponent,
    UsersGridComponent,
    PaginationComponent,
    PairsPipe,
    NewDareComponent,
    UserCardComponent,
    UserProfileComponent,
    DaresGridComponent,
    AdminNavbarComponent,
    AdminHomeComponent,
    AdminDaresComponent,
    AdminUsersComponent,
    AdminSponsorsComponent,
    AdminConfigComponent,
    AdminCategoriesComponent
  ],
  imports: [
    RouterModule.forRoot(applicationRoutes),
    BrowserModule,
    FormsModule,
    HttpModule,
    ReactiveFormsModule
  ],
  providers: [
    AuthenticationService,
    DareuPublicBackendService,
    DareuUserBackendService,
    AuthGuard,
    Logger,
    AdminBackendService,
    ServerUtilities
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(private logger: Logger) {
    this.logger.level = environment.logger.level;
  }

}
