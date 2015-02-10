package com.zizibujuan.niubizi.server.model;

import javax.persistence.EntityManagerFactory;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class Activator implements BundleActivator, ServiceTrackerCustomizer<EntityManagerFactory, EntityManagerFactory> {

	private static BundleContext context;
	private ServiceTracker<EntityManagerFactory, EntityManagerFactory> emfTracker;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		emfTracker = new ServiceTracker<EntityManagerFactory, EntityManagerFactory>(bundleContext, EntityManagerFactory.class.getName(), this);
		emfTracker.open();
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		emfTracker.close();
	}

	@Override
	public EntityManagerFactory addingService(
			ServiceReference<EntityManagerFactory> reference) {
		EntityManagerFactory entityManagerFactory = reference.getBundle().getBundleContext().getService(reference);
	    EntityManagerFactoryService.setEmf(entityManagerFactory);
		return entityManagerFactory;
	}

	@Override
	public void modifiedService(
			ServiceReference<EntityManagerFactory> reference,
			EntityManagerFactory service) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removedService(
			ServiceReference<EntityManagerFactory> reference,
			EntityManagerFactory service) {
		// TODO Auto-generated method stub
		
	}




}
