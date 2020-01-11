import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecommenderBusinessRequirementsComponent } from './recommender-business-requirements.component';

describe('RecommenderBusinessRequirementsComponent', () => {
  let component: RecommenderBusinessRequirementsComponent;
  let fixture: ComponentFixture<RecommenderBusinessRequirementsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecommenderBusinessRequirementsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecommenderBusinessRequirementsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
