import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RecommenderModelComponent } from './recommender-model/recommender-model.component';
import { RecommenderBusinessRequirementsComponent } from './recommender-business-requirements/recommender-business-requirements.component';
import { RecommenderMeasurementsComponent } from './recommender-measurements/recommender-measurements.component';
import { RecommenderApplicationComponent } from './recommender-application/recommender-application.component';
import { RecommenderConclusionComponent } from './recommender-conclusion/recommender-conclusion.component';
import { RecommenderApplicationPerUserComponent } from './recommender-application-per-user/recommender-application-per-user.component';


const appRoutes: Routes = [
  { path: 'model', component: RecommenderModelComponent },
  { path: 'business-requirements', component: RecommenderBusinessRequirementsComponent },
  { path: 'measurements', component: RecommenderMeasurementsComponent },
  { path: 'application', component: RecommenderApplicationComponent },
  { path: 'conclusion', component: RecommenderConclusionComponent },
  { path: 'application-per-user', component: RecommenderApplicationPerUserComponent },
  { path: '**', component: RecommenderApplicationComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
