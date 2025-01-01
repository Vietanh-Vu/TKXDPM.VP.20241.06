package isd.aims.main.controller.payment;

import isd.aims.main.entity.payment.PaymentType;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class PaymentMethodFactory {
    private static final PaymentMethodFactory INSTANCE = new PaymentMethodFactory();
    private final Map<String, IPaymentMethod> paymentMethodMap = new HashMap<>();
    private boolean initialized = false;

    private PaymentMethodFactory() {
    }

    public static PaymentMethodFactory getInstance() {
        if (!INSTANCE.initialized) {
            INSTANCE.initializePaymentMethods();
        }
        return INSTANCE;
    }

    private void initializePaymentMethods() {
        try {
            // Quét package chứa các payment methods
            String packageName = "isd.aims.main.InterbankSubsystem";
            String packagePath = packageName.replace('.', '/');

            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Enumeration<URL> resources = classLoader.getResources(packagePath);

            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                File directory = new File(resource.getFile());
                if (directory.exists()) {
                    registerPaymentMethodsInDirectory(directory, packageName);
                }
            }

            initialized = true;
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize payment methods", e);
        }
    }

    private void registerPaymentMethodsInDirectory(File directory, String packageName) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    registerPaymentMethodsInDirectory(file, packageName + "." + file.getName());
                } else if (file.getName().endsWith("PaymentMethod.class")) {
                    try {
                        String className = packageName + "." +
                                file.getName().substring(0, file.getName().length() - 6);
                        Class<?> clazz = Class.forName(className);

                        if (IPaymentMethod.class.isAssignableFrom(clazz) &&
                                !clazz.isInterface()) {
                            IPaymentMethod paymentMethod =
                                    (IPaymentMethod) clazz.getDeclaredConstructor().newInstance();
                            registerPaymentMethod(paymentMethod);
                        }
                    } catch (Exception e) {
                        // Log error but continue processing other classes
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void registerPaymentMethod(IPaymentMethod paymentMethod) {
        paymentMethodMap.put(paymentMethod.getType(), paymentMethod);
    }

    public IPaymentMethod createPaymentMethod(PaymentType paymentType) {
        IPaymentMethod paymentMethod = paymentMethodMap.get(paymentType.getPaymentType());
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Unsupported payment type: " + paymentType);
        }
        return paymentMethod;
    }
}
