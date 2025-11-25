import { SfeirThemeInitializer } from '../web_modules/sfeir-school-theme/sfeir-school-theme.mjs';

// One method per module
function schoolSlides() {
  return ['00-school/00-TITLE.md', '00-school/speaker-bm.md'];
}

function casPratique() {
  return ['50-cas-pratique/51-intro.md', '50-cas-pratique/52-microservice.md'];
}

function rexSlides() {
  return [
    '01-intro/intro.md',
    '10-conceptions/11-conceptions.md',
    '20-architectures/21-architectures.md',
    '20-architectures/22-mach.md',
    '20-architectures/23-authentication.md',
    '30-conclusion/31-conclu.md',
  ];
}

function iaSlides() {
  return [
    '60-ai/ia.md'
  ];
}

function breakSlide() {
  return ['99-break.md'];
}

function formation() {
  return [
    //
    ...schoolSlides(), //
    ...rexSlides(), //
    ...breakSlide(), //
    ...casPratique(), //
    ...breakSlide(), //
    ...iaSlides(), //
  ].map((slidePath) => {
    return { path: slidePath };
  });
}

SfeirThemeInitializer.init(formation);
