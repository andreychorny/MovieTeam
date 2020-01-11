import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatMenuModule } from '@angular/material/menu';
import { RecommenderModelComponent } from './recommender-model/recommender-model.component';
import { RecommenderBusinessRequirementsComponent } from './recommender-business-requirements/recommender-business-requirements.component';
import { RecommenderMeasurementsComponent } from './recommender-measurements/recommender-measurements.component';
import { RecommenderApplicationComponent } from './recommender-application/recommender-application.component';
import { RecommenderConclusionComponent } from './recommender-conclusion/recommender-conclusion.component';

@NgModule({
  declarations: [
    AppComponent,
    RecommenderModelComponent,
    RecommenderBusinessRequirementsComponent,
    RecommenderMeasurementsComponent,
    RecommenderApplicationComponent,
    RecommenderConclusionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    MatMenuModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
