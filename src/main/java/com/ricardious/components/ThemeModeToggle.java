package com.ricardious.components;

import javafx.animation.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.paint.CycleMethod;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

public class ThemeModeToggle extends Parent {

    private BooleanProperty switchedOn = new SimpleBooleanProperty(false);
    private TranslateTransition translateAnimation;
    private TranslateTransition sunAnimation;
    private TranslateTransition moonAnimation;
    private ScaleTransition stretchX;

    // Define gradients as class fields for easy access
    private final LinearGradient lightModeGradient = new LinearGradient(
            0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.valueOf("#FF8A00")),
            new Stop(0.5, Color.valueOf("#FFA940")),
            new Stop(1, Color.valueOf("#FFB266"))
    );

    private final LinearGradient darkModeGradient = new LinearGradient(
            0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.valueOf("#4A148C")),
            new Stop(0.5, Color.valueOf("#6A1B9A")),
            new Stop(1, Color.valueOf("#7B1FA2"))
    );

    public ThemeModeToggle() {
        Rectangle background = new Rectangle(100, 50);
        background.setArcWidth(background.getHeight());
        background.setArcHeight(background.getHeight());
        background.setFill(lightModeGradient);

        // Enhanced inner shadow for background
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setBlurType(BlurType.GAUSSIAN);
        innerShadow.setColor(Color.rgb(0, 0, 0, 0.2));  // More pronounced shadow
        innerShadow.setRadius(background.getHeight() * 0.15);  // Increased radius
        innerShadow.setOffsetX(background.getWidth() * 0.03);
        innerShadow.setOffsetY(background.getHeight() * 0.06);
        background.setEffect(innerShadow);

        double circleRadius = background.getHeight() * 0.4;
        Circle trigger = new Circle(circleRadius);
        trigger.setFill(Color.WHITE);

        // Enhanced shadow effects for trigger
        DropShadow outerShadow = new DropShadow();
        outerShadow.setBlurType(BlurType.GAUSSIAN);
        outerShadow.setColor(Color.rgb(0, 0, 0, 0.25));  // More pronounced shadow
        outerShadow.setRadius(circleRadius * 0.4);  // Increased radius
        outerShadow.setOffsetX(circleRadius * 0.2);
        outerShadow.setOffsetY(circleRadius * 0.2);

        InnerShadow circleInnerShadow = new InnerShadow();
        circleInnerShadow.setBlurType(BlurType.GAUSSIAN);
        circleInnerShadow.setColor(Color.rgb(0, 0, 0, 0.15));  // More pronounced shadow
        circleInnerShadow.setRadius(circleRadius * 0.45);  // Increased radius
        circleInnerShadow.setOffsetX(-circleRadius * 0.25);
        circleInnerShadow.setOffsetY(-circleRadius * 0.25);

        outerShadow.setInput(circleInnerShadow);
        trigger.setEffect(outerShadow);

        SVGPath sunIcon = createSunIcon();
        SVGPath moonIcon = createMoonIcon();

        double iconScale = circleRadius * 0.05;
        sunIcon.setScaleX(iconScale);
        sunIcon.setScaleY(iconScale);
        moonIcon.setScaleX(iconScale);
        moonIcon.setScaleY(iconScale);

        double horizontalOffset = -circleRadius * 0.6;
        double verticalOffset = -circleRadius * 0.6;
        sunIcon.setTranslateX(horizontalOffset);
        sunIcon.setTranslateY(verticalOffset);
        moonIcon.setTranslateX(horizontalOffset);
        moonIcon.setTranslateY(verticalOffset);

        moonIcon.setOpacity(0);
        sunIcon.setOpacity(1);

        Group iconGroup = new Group(sunIcon, moonIcon);
        Group circleGroup = new Group(trigger, iconGroup);

        double horizontalPosition = background.getWidth() * 0.25;
        double verticalPosition = background.getHeight() * 0.5;
        circleGroup.setTranslateX(horizontalPosition);
        circleGroup.setTranslateY(verticalPosition);

        setupIconAnimations(sunIcon, moonIcon, circleRadius);
        setupMainAnimations(background, circleGroup, trigger, innerShadow, background.getWidth());
        setupHorizontalStretchAnimation(trigger, circleRadius);

        getChildren().addAll(background, circleGroup);

        setOnMouseClicked(event -> {
            switchedOn.set(!switchedOn.get());
        });
    }

    private SVGPath createSunIcon() {
        SVGPath sunIcon = new SVGPath();
        sunIcon.setContent("M12 2.25a.75.75 0 0 1 .75.75v2.25a.75.75 0 0 1-1.5 0V3a.75.75 0 0 1 .75-.75ZM7.5 12a4.5 4.5 0 1 1 9 0 4.5 4.5 0 0 1-9 0ZM18.894 6.166a.75.75 0 0 0-1.06-1.06l-1.591 1.59a.75.75 0 1 0 1.06 1.061l1.591-1.59ZM21.75 12a.75.75 0 0 1-.75.75h-2.25a.75.75 0 0 1 0-1.5H21a.75.75 0 0 1 .75.75ZM17.834 18.894a.75.75 0 0 0 1.06-1.06l-1.59-1.591a.75.75 0 1 0-1.061 1.06l1.59 1.591ZM12 18a.75.75 0 0 1 .75.75V21a.75.75 0 0 1-1.5 0v-2.25A.75.75 0 0 1 12 18ZM7.758 17.303a.75.75 0 0 0-1.061-1.06l-1.591 1.59a.75.75 0 0 0 1.06 1.061l1.591-1.59ZM6 12a.75.75 0 0 1-.75.75H3a.75.75 0 0 1 0-1.5h2.25A.75.75 0 0 1 6 12ZM6.697 7.757a.75.75 0 0 0 1.06-1.06l-1.59-1.591a.75.75 0 0 0-1.061 1.06l1.59 1.591Z");
        sunIcon.setFill(Color.valueOf("#FF6B00"));  // Warmer orange color
        return sunIcon;
    }

    private SVGPath createMoonIcon() {
        SVGPath moonIcon = new SVGPath();
        moonIcon.setContent("M9.528 1.718a.75.75 0 0 1 .162.819A8.97 8.97 0 0 0 9 6a9 9 0 0 0 9 9 8.97 8.97 0 0 0 3.463-.69.75.75 0 0 1 .981.98 10.503 10.503 0 0 1-9.694 6.46c-5.799 0-10.5-4.7-10.5-10.5 0-4.368 2.667-8.112 6.46-9.694a.75.75 0 0 1 .818.162Z");
        moonIcon.setFill(Color.valueOf("#E1BEE7"));  // Light purple color
        return moonIcon;
    }

    private void setupIconAnimations(SVGPath sunIcon, SVGPath moonIcon, double circleRadius) {
        // Previous animation setup remains the same
        sunAnimation = new TranslateTransition(Duration.seconds(0.5), sunIcon);
        moonAnimation = new TranslateTransition(Duration.seconds(0.5), moonIcon);

        FadeTransition sunFade = new FadeTransition(Duration.seconds(0.5), sunIcon);
        FadeTransition moonFade = new FadeTransition(Duration.seconds(0.5), moonIcon);

        switchedOn.addListener((obs, oldState, newState) -> {
            if (newState) {
                sunAnimation.setFromY(-circleRadius * 0.6);
                sunAnimation.setToY(-circleRadius * 2);
                moonAnimation.setFromY(circleRadius * 0.6);
                moonAnimation.setToY(-circleRadius * 0.6);

                sunFade.setFromValue(1.0);
                sunFade.setToValue(0.0);
                moonFade.setFromValue(0.0);
                moonFade.setToValue(1.0);
            } else {
                sunAnimation.setFromY(-circleRadius * 2);
                sunAnimation.setToY(-circleRadius * 0.6);
                moonAnimation.setFromY(-circleRadius * 0.6);
                moonAnimation.setToY(circleRadius * 0.6);

                sunFade.setFromValue(0.0);
                sunFade.setToValue(1.0);
                moonFade.setFromValue(1.0);
                moonFade.setToValue(0.0);
            }

            ParallelTransition parallelTransition = new ParallelTransition(
                    sunAnimation,
                    moonAnimation,
                    sunFade,
                    moonFade
            );
            parallelTransition.play();
        });
    }

    private void setupMainAnimations(Rectangle background, Group circleGroup, Circle trigger,
                                     InnerShadow innerShadow, double backgroundWidth) {
        translateAnimation = new TranslateTransition(Duration.seconds(0.5), circleGroup);

        switchedOn.addListener((obs, oldState, newState) -> {
            boolean isOn = newState.booleanValue();

            translateAnimation.setToX(isOn ? backgroundWidth * 0.75 : backgroundWidth * 0.25);

            // Use custom transition for gradient changes
            Timeline gradientTransition = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(background.fillProperty(),
                                    isOn ? lightModeGradient : darkModeGradient)),
                    new KeyFrame(Duration.seconds(0.5),
                            new KeyValue(background.fillProperty(),
                                    isOn ? darkModeGradient : lightModeGradient))
            );

            Timeline triggerColorTransition = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(trigger.fillProperty(),
                                    isOn ? Color.WHITE : Color.valueOf("#2C2C2F"))),
                    new KeyFrame(Duration.seconds(0.5),
                            new KeyValue(trigger.fillProperty(),
                                    isOn ? Color.valueOf("#2C2C2F") : Color.WHITE))
            );

            innerShadow.setColor(isOn ?
                    Color.rgb(0, 0, 0, 0.35) :  // Darker shadow for dark mode
                    Color.rgb(0, 0, 0, 0.2));   // Lighter shadow for light mode

            ParallelTransition parallelTransition = new ParallelTransition(
                    translateAnimation,
                    gradientTransition,
                    triggerColorTransition
            );
            parallelTransition.play();
        });
    }

    private void setupHorizontalStretchAnimation(Circle trigger, double circleRadius) {
        stretchX = new ScaleTransition(Duration.millis(100), trigger);
        stretchX.setAutoReverse(true);
        stretchX.setCycleCount(2);
        stretchX.setFromX(1.0);
        stretchX.setToX(1.3);
        stretchX.setFromY(1.0);
        stretchX.setToY(0.9);

        setOnMousePressed(event -> {
            stretchX.play();
        });
    }

    public BooleanProperty switchedOnProperty() {
        return switchedOn;
    }

    public boolean isSwitchedOn() {
        return switchedOn.get();
    }

    public void setSwitchedOn(boolean state) {
        switchedOn.set(state);
    }
}