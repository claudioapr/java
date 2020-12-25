package com.staxter.message;

/**
 * This enumeration aims to provide specification for Action to be taken when
 * the limit of message from a player is reached
 * 
 * @author cresende
 */
public enum LimitAction
{
 /**
  * This action shall disconnect the player from the other players that it is
  * connected to, when the limit of message is reached
  */
 END_ONLY_CHAT,

 /**
  * This action shall terminate the chat closing the program, when the limit of
  * message is reached
  */
 END_SYSTEM,

 /**
  * This action shall do nothing when the limit of message is reached
  */
 DO_NOTHING
}
