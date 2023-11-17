package com.helpfinance.application.models.movimentation;

import java.time.LocalDateTime;
import java.util.UUID;

import com.helpfinance.domain.enums.MovimentationCategory;
import com.helpfinance.domain.enums.MovimentationType;

public class MovimentationAddModel {
  public UUID userId;
  public String title;
  public int amount;
  public MovimentationCategory category;
  public MovimentationType movType;
  public LocalDateTime date;
}
