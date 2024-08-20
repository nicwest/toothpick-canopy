color ([0.8, 0.8, 0.8, ]) {
  union () {
    translate ([0, 0, 9]) {
      cylinder ($fn=30, h=18, r=0.75, center=true);
    }
    translate ([25.5, 25.5, 9]) {
      cylinder ($fn=30, h=18, r=0.75, center=true);
    }
    translate ([0, 25.5, 9/2]) {
      cylinder ($fn=30, h=9, r=0.75, center=true);
    }
    translate ([25.5, 0, 9]) {
      cylinder ($fn=30, h=18, r=0.75, center=true);
    }
  }
}
color ([0.2, 0.2, 0.2, ]) {
  difference () {
    translate ([12.75, 12.75, 13/2]) {
      cube ([29.0, 29.0, 1], center=true);
    }
    union () {
      translate ([0, 0, 9]) {
        cylinder ($fn=30, h=18, r=1.25, center=true);
      }
      translate ([25.5, 25.5, 9]) {
        cylinder ($fn=30, h=18, r=1.25, center=true);
      }
      translate ([0, 25.5, 9/2]) {
        cylinder ($fn=30, h=9, r=1.25, center=true);
      }
      translate ([25.5, 0, 9]) {
        cylinder ($fn=30, h=18, r=1.25, center=true);
      }
    }
  }
}
color ([0.2, 0.2, 0.2, ]) {
  difference () {
    union () {
      translate ([12.75, 12.75, 13.0]) {
        cube ([29.0, 29.0, 1], center=true);
      }
    }
    union () {
      translate ([0, 0, 9]) {
        cylinder ($fn=30, h=18, r=1.25, center=true);
      }
      translate ([25.5, 25.5, 9]) {
        cylinder ($fn=30, h=18, r=1.25, center=true);
      }
      translate ([0, 25.5, 9/2]) {
        cylinder ($fn=30, h=9, r=1.25, center=true);
      }
      translate ([25.5, 0, 9]) {
        cylinder ($fn=30, h=18, r=1.25, center=true);
      }
    }
    rotate (a=45.0, v=[0, 0, 1]) {
      translate ([0, 53.82842712474619, 51.0]) {
        cube ([102.0, 102.0, 102.0], center=true);
      }
    }
  }
}
translate ([7.093145750507619, 18.40685424949238, 16]) {
  rotate (a=45.0, v=[0, 0, 1]) {
    rotate (a=-70.0, v=[1, 0, 0]) {
      union () {
        color ([0.2, 0.2, 0.2, ]) {
          translate ([0, 0, 1.25]) {
            cube ([14, 14, 2.5], center=true);
          }
        }
        color ([0, 0, 0, ]) {
          translate ([0, 0, 9.0]) {
            cylinder (h=13, r=4.5, center=true);
          }
        }
      }
    }
  }
}
difference () {
  union () {
    difference () {
      hull () {
        translate ([7.093145750507619, 18.40685424949238, 16]) {
          rotate (a=45.0, v=[0, 0, 1]) {
            rotate (a=-70.0, v=[1, 0, 0]) {
              translate ([0, 7.95, 1.25]) {
                cube ([17.4, 1.5, 2.5], center=true);
              }
            }
          }
        }
        translate ([0, 25.5, 7]) {
          translate ([0, 0, 0.75]) {
            cylinder ($fn=30, h=1.5, r=2.3, center=true);
          }
        }
      }
      translate ([0, 25.5, 57.5]) {
        cylinder ($fn=6, h=100, r=2.3, center=true);
      }
    }
    difference () {
      hull () {
        translate ([7.093145750507619, 18.40685424949238, 16]) {
          rotate (a=45.0, v=[0, 0, 1]) {
            rotate (a=-70.0, v=[1, 0, 0]) {
              translate ([0, -7.95, 1.25]) {
                cube ([17.4, 1.5, 2.5], center=true);
              }
            }
          }
        }
        translate ([25.5, 0, 13.5]) {
          translate ([0, 0, 0.75]) {
            cylinder ($fn=30, h=1.5, r=2.3, center=true);
          }
        }
      }
      translate ([25.5, 0, 65.0]) {
        cylinder ($fn=6, h=100, r=2.8, center=true);
      }
    }
    union () {
      translate ([0, 0, 13.5]) {
        translate ([0, 0, 0.75]) {
          cylinder ($fn=30, h=1.5, r=2.3, center=true);
        }
      }
      difference () {
        hull () {
          translate ([7.093145750507619, 18.40685424949238, 16]) {
            rotate (a=45.0, v=[0, 0, 1]) {
              rotate (a=-70.0, v=[1, 0, 0]) {
                translate ([-7.95, 0, 1.25]) {
                  cube ([1.5, 17.4, 2.5], center=true);
                }
              }
            }
          }
          translate ([0, 0, 13.5]) {
            rotate (a=155.0, v=[0, 0, 1]) {
              difference () {
                intersection () {
                  translate ([1.9, 1.9, 1.9]) {
                    cube ([3.8, 3.8, 3.8], center=true);
                  }
                  cylinder ($fn=30, h=4, r=3.8, center=true);
                }
                cylinder ($fn=30, h=12, r=2.3, center=true);
              }
            }
          }
        }
        translate ([0, 0, -36.5]) {
          cylinder ($fn=30, h=100, r=3.8, center=true);
        }
        translate ([0, 0, 65.0]) {
          cylinder ($fn=6, h=100, r=2.3, center=true);
        }
        color ([0.2, 0.2, 0.2, ]) {
          difference () {
            union () {
              translate ([12.75, 12.75, 6.75]) {
                cube ([29.0, 29.0, 13.5], center=true);
              }
            }
            union () {
              translate ([0, 0, 9]) {
                cylinder ($fn=30, h=18, r=1.25, center=true);
              }
              translate ([25.5, 25.5, 9]) {
                cylinder ($fn=30, h=18, r=1.25, center=true);
              }
              translate ([0, 25.5, 9/2]) {
                cylinder ($fn=30, h=9, r=1.25, center=true);
              }
              translate ([25.5, 0, 9]) {
                cylinder ($fn=30, h=18, r=1.25, center=true);
              }
            }
            rotate (a=45.0, v=[0, 0, 1]) {
              translate ([0, 58.071067811865476, 51.0]) {
                cube ([102.0, 102.0, 102.0], center=true);
              }
            }
          }
        }
        translate ([4.596194077712559, 4.596194077712559, 13.5]) {
          rotate (a=135.0, v=[0, 0, 1]) {
            cube ([11, 8, 8], center=true);
          }
        }
      }
    }
    union () {
      translate ([25.5, 25.5, 13.5]) {
        translate ([0, 0, 0.75]) {
          cylinder ($fn=30, h=1.5, r=2.3, center=true);
        }
      }
      difference () {
        hull () {
          translate ([7.093145750507619, 18.40685424949238, 16]) {
            rotate (a=45.0, v=[0, 0, 1]) {
              rotate (a=-70.0, v=[1, 0, 0]) {
                translate ([7.95, 0, 1.25]) {
                  cube ([1.5, 17.4, 2.5], center=true);
                }
              }
            }
          }
          translate ([25.5, 25.5, 13.5]) {
            rotate (a=35.0, v=[0, 0, 1]) {
              difference () {
                intersection () {
                  translate ([1.9, 1.9, 1.9]) {
                    cube ([3.8, 3.8, 3.8], center=true);
                  }
                  cylinder ($fn=30, h=4, r=3.8, center=true);
                }
                cylinder ($fn=30, h=12, r=2.3, center=true);
              }
            }
          }
        }
        translate ([25.5, 25.5, -36.5]) {
          cylinder ($fn=30, h=100, r=3.8, center=true);
        }
        translate ([25.5, 25.5, 65.0]) {
          cylinder ($fn=6, h=100, r=2.3, center=true);
        }
        color ([0.2, 0.2, 0.2, ]) {
          difference () {
            union () {
              translate ([12.75, 12.75, 6.75]) {
                cube ([29.0, 29.0, 13.5], center=true);
              }
            }
            union () {
              translate ([0, 0, 9]) {
                cylinder ($fn=30, h=18, r=1.25, center=true);
              }
              translate ([25.5, 25.5, 9]) {
                cylinder ($fn=30, h=18, r=1.25, center=true);
              }
              translate ([0, 25.5, 9/2]) {
                cylinder ($fn=30, h=9, r=1.25, center=true);
              }
              translate ([25.5, 0, 9]) {
                cylinder ($fn=30, h=18, r=1.25, center=true);
              }
            }
            rotate (a=45.0, v=[0, 0, 1]) {
              translate ([0, 58.071067811865476, 51.0]) {
                cube ([102.0, 102.0, 102.0], center=true);
              }
            }
          }
        }
        translate ([20.90380592228744, 20.90380592228744, 13.5]) {
          rotate (a=135.0, v=[0, 0, 1]) {
            cube ([11, 8, 8], center=true);
          }
        }
      }
    }
    translate ([7.093145750507619, 18.40685424949238, 16]) {
      rotate (a=45.0, v=[0, 0, 1]) {
        rotate (a=-70.0, v=[1, 0, 0]) {
          translate ([0, 0, 27/2]) {
            difference () {
              cylinder ($fn=30, h=5, r=6.7, center=true);
              cylinder ($fn=30, h=10000, r=4.7, center=true);
              union () {
                translate ([0, 0, 5/2]) {
                  rotate (a=0.0, v=[0, 0, 1]) {
                    cube ([1000, 2, 2], center=true);
                  }
                }
                translate ([0, 0, 5/2]) {
                  rotate (a=59.99999999999999, v=[0, 0, 1]) {
                    cube ([1000, 2, 2], center=true);
                  }
                }
                translate ([0, 0, 5/2]) {
                  rotate (a=119.99999999999999, v=[0, 0, 1]) {
                    cube ([1000, 2, 2], center=true);
                  }
                }
                translate ([0, 0, 5/2]) {
                  rotate (a=180.0, v=[0, 0, 1]) {
                    cube ([1000, 2, 2], center=true);
                  }
                }
                translate ([0, 0, 5/2]) {
                  rotate (a=239.99999999999997, v=[0, 0, 1]) {
                    cube ([1000, 2, 2], center=true);
                  }
                }
                translate ([0, 0, 5/2]) {
                  rotate (a=299.99999999999994, v=[0, 0, 1]) {
                    cube ([1000, 2, 2], center=true);
                  }
                }
                translate ([0, 0, 5/2]) {
                  rotate (a=359.99999999999994, v=[0, 0, 1]) {
                    cube ([1000, 2, 2], center=true);
                  }
                }
              }
            }
          }
        }
      }
    }
    translate ([7.093145750507619, 18.40685424949238, 16]) {
      rotate (a=45.0, v=[0, 0, 1]) {
        rotate (a=-70.0, v=[1, 0, 0]) {
          difference () {
            union () {
              difference () {
                union () {
                  hull () {
                    translate ([0, 0, 27/2]) {
                      rotate (a=-67.5, v=[0, 0, 1]) {
                        intersection () {
                          rotate (a=45.0, v=[0, 0, 1]) {
                            intersection () {
                              translate ([50, 50, 50]) {
                                cube ([100, 100, 100], center=true);
                              }
                              difference () {
                                cylinder (h=1, r=6.5, center=true);
                                cylinder (h=100, r=4.7, center=true);
                              }
                            }
                          }
                          translate ([50, 50, 50]) {
                            cube ([100, 100, 100], center=true);
                          }
                        }
                      }
                    }
                    translate ([7.95, 0, 6.5]) {
                      cube ([1.5, 5, 1], center=true);
                    }
                  }
                  translate ([7.95, 0, 3.25]) {
                    cube ([1.5, 5, 6.5], center=true);
                  }
                }
                cylinder ($fn=30, h=1000, r=4.7, center=true);
              }
              rotate (a=90.0, v=[0, 0, 1]) {
                difference () {
                  union () {
                    hull () {
                      translate ([0, 0, 27/2]) {
                        rotate (a=-67.5, v=[0, 0, 1]) {
                          intersection () {
                            rotate (a=45.0, v=[0, 0, 1]) {
                              intersection () {
                                translate ([50, 50, 50]) {
                                  cube ([100, 100, 100], center=true);
                                }
                                difference () {
                                  cylinder (h=1, r=6.5, center=true);
                                  cylinder (h=100, r=4.7, center=true);
                                }
                              }
                            }
                            translate ([50, 50, 50]) {
                              cube ([100, 100, 100], center=true);
                            }
                          }
                        }
                      }
                      translate ([7.95, 0, 6.5]) {
                        cube ([1.5, 5, 1], center=true);
                      }
                    }
                    translate ([7.95, 0, 3.25]) {
                      cube ([1.5, 5, 6.5], center=true);
                    }
                  }
                  cylinder ($fn=30, h=1000, r=4.7, center=true);
                }
              }
              rotate (a=180.0, v=[0, 0, 1]) {
                difference () {
                  union () {
                    hull () {
                      translate ([0, 0, 27/2]) {
                        rotate (a=-67.5, v=[0, 0, 1]) {
                          intersection () {
                            rotate (a=45.0, v=[0, 0, 1]) {
                              intersection () {
                                translate ([50, 50, 50]) {
                                  cube ([100, 100, 100], center=true);
                                }
                                difference () {
                                  cylinder (h=1, r=6.5, center=true);
                                  cylinder (h=100, r=4.7, center=true);
                                }
                              }
                            }
                            translate ([50, 50, 50]) {
                              cube ([100, 100, 100], center=true);
                            }
                          }
                        }
                      }
                      translate ([7.95, 0, 6.5]) {
                        cube ([1.5, 5, 1], center=true);
                      }
                    }
                    translate ([7.95, 0, 3.25]) {
                      cube ([1.5, 5, 6.5], center=true);
                    }
                  }
                  cylinder ($fn=30, h=1000, r=4.7, center=true);
                }
              }
              rotate (a=270.0, v=[0, 0, 1]) {
                difference () {
                  union () {
                    hull () {
                      translate ([0, 0, 27/2]) {
                        rotate (a=-67.5, v=[0, 0, 1]) {
                          intersection () {
                            rotate (a=45.0, v=[0, 0, 1]) {
                              intersection () {
                                translate ([50, 50, 50]) {
                                  cube ([100, 100, 100], center=true);
                                }
                                difference () {
                                  cylinder (h=1, r=6.5, center=true);
                                  cylinder (h=100, r=4.7, center=true);
                                }
                              }
                            }
                            translate ([50, 50, 50]) {
                              cube ([100, 100, 100], center=true);
                            }
                          }
                        }
                      }
                      translate ([7.95, 0, 6.5]) {
                        cube ([1.5, 5, 1], center=true);
                      }
                    }
                    translate ([7.95, 0, 3.25]) {
                      cube ([1.5, 5, 6.5], center=true);
                    }
                  }
                  cylinder ($fn=30, h=1000, r=4.7, center=true);
                }
              }
            }
            translate ([0, 0, 5.0]) {
              rotate (a=90.0, v=[0, 1, 0]) {
                cylinder ($fn=30, h=1000, r=1, center=true);
              }
            }
          }
        }
      }
    }
    translate ([7.093145750507619, 18.40685424949238, 16]) {
      rotate (a=45.0, v=[0, 0, 1]) {
        rotate (a=-70.0, v=[1, 0, 0]) {
          union () {
            translate ([8, -10.75, 0]) {
              difference () {
                rotate (a=90.0, v=[0, 1, 0]) {
                  difference () {
                    cylinder ($fn=30, h=4, r=3.5, center=true);
                    cylinder ($fn=30, h=1000, r=2.5, center=true);
                  }
                }
                rotate (a=90.0, v=[0, 1, 0]) {
                  translate ([0, -3, 0]) {
                    cube ([2, 2, 1000], center=true);
                  }
                }
              }
            }
            translate ([-8, -10.75, 0]) {
              rotate (a=90.0, v=[0, 1, 0]) {
                difference () {
                  cylinder ($fn=30, h=4, r=3.5, center=true);
                  cylinder ($fn=30, h=1000, r=2.5, center=true);
                }
              }
            }
          }
        }
      }
    }
  }
  union () {
    translate ([0, 0, 9]) {
      cylinder ($fn=30, h=18, r=1.25, center=true);
    }
    translate ([25.5, 25.5, 9]) {
      cylinder ($fn=30, h=18, r=1.25, center=true);
    }
    translate ([0, 25.5, 9/2]) {
      cylinder ($fn=30, h=9, r=1.25, center=true);
    }
    translate ([25.5, 0, 9]) {
      cylinder ($fn=30, h=18, r=1.25, center=true);
    }
  }
}
