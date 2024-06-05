package cdm.product.collateral;

import cdm.product.collateral.DeliveryAmount;
import cdm.product.collateral.InterestAmountApplication;
import cdm.product.collateral.InterestAmountApplication.InterestAmountApplicationBuilder;
import cdm.product.collateral.InterestAmountApplication.InterestAmountApplicationBuilderImpl;
import cdm.product.collateral.InterestAmountApplication.InterestAmountApplicationImpl;
import cdm.product.collateral.ReturnAmount;
import cdm.product.collateral.meta.InterestAmountApplicationMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class to specify the application of Interest Amount with respect to the Delivery Amount and the Return Amount.
 * @version ${project.version}
 */
@RosettaDataType(value="InterestAmountApplication", builder=InterestAmountApplication.InterestAmountApplicationBuilderImpl.class, version="${project.version}")
public interface InterestAmountApplication extends RosettaModelObject {

	InterestAmountApplicationMeta metaData = new InterestAmountApplicationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The application of Interest Amount with respect the Return Amount.
	 */
	ReturnAmount getReturnAmount();
	/**
	 * The application of Interest Amount with respect the Delivery Amount.
	 */
	DeliveryAmount getDeliveryAmount();

	/*********************** Build Methods  ***********************/
	InterestAmountApplication build();
	
	InterestAmountApplication.InterestAmountApplicationBuilder toBuilder();
	
	static InterestAmountApplication.InterestAmountApplicationBuilder builder() {
		return new InterestAmountApplication.InterestAmountApplicationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends InterestAmountApplication> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends InterestAmountApplication> getType() {
		return InterestAmountApplication.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("returnAmount"), processor, ReturnAmount.class, getReturnAmount());
		processRosetta(path.newSubPath("deliveryAmount"), processor, DeliveryAmount.class, getDeliveryAmount());
	}
	

	/*********************** Builder Interface  ***********************/
	interface InterestAmountApplicationBuilder extends InterestAmountApplication, RosettaModelObjectBuilder {
		ReturnAmount.ReturnAmountBuilder getOrCreateReturnAmount();
		ReturnAmount.ReturnAmountBuilder getReturnAmount();
		DeliveryAmount.DeliveryAmountBuilder getOrCreateDeliveryAmount();
		DeliveryAmount.DeliveryAmountBuilder getDeliveryAmount();
		InterestAmountApplication.InterestAmountApplicationBuilder setReturnAmount(ReturnAmount returnAmount);
		InterestAmountApplication.InterestAmountApplicationBuilder setDeliveryAmount(DeliveryAmount deliveryAmount);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("returnAmount"), processor, ReturnAmount.ReturnAmountBuilder.class, getReturnAmount());
			processRosetta(path.newSubPath("deliveryAmount"), processor, DeliveryAmount.DeliveryAmountBuilder.class, getDeliveryAmount());
		}
		

		InterestAmountApplication.InterestAmountApplicationBuilder prune();
	}

	/*********************** Immutable Implementation of InterestAmountApplication  ***********************/
	class InterestAmountApplicationImpl implements InterestAmountApplication {
		private final ReturnAmount returnAmount;
		private final DeliveryAmount deliveryAmount;
		
		protected InterestAmountApplicationImpl(InterestAmountApplication.InterestAmountApplicationBuilder builder) {
			this.returnAmount = ofNullable(builder.getReturnAmount()).map(f->f.build()).orElse(null);
			this.deliveryAmount = ofNullable(builder.getDeliveryAmount()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("returnAmount")
		public ReturnAmount getReturnAmount() {
			return returnAmount;
		}
		
		@Override
		@RosettaAttribute("deliveryAmount")
		public DeliveryAmount getDeliveryAmount() {
			return deliveryAmount;
		}
		
		@Override
		public InterestAmountApplication build() {
			return this;
		}
		
		@Override
		public InterestAmountApplication.InterestAmountApplicationBuilder toBuilder() {
			InterestAmountApplication.InterestAmountApplicationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(InterestAmountApplication.InterestAmountApplicationBuilder builder) {
			ofNullable(getReturnAmount()).ifPresent(builder::setReturnAmount);
			ofNullable(getDeliveryAmount()).ifPresent(builder::setDeliveryAmount);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			InterestAmountApplication _that = getType().cast(o);
		
			if (!Objects.equals(returnAmount, _that.getReturnAmount())) return false;
			if (!Objects.equals(deliveryAmount, _that.getDeliveryAmount())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (returnAmount != null ? returnAmount.hashCode() : 0);
			_result = 31 * _result + (deliveryAmount != null ? deliveryAmount.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "InterestAmountApplication {" +
				"returnAmount=" + this.returnAmount + ", " +
				"deliveryAmount=" + this.deliveryAmount +
			'}';
		}
	}

	/*********************** Builder Implementation of InterestAmountApplication  ***********************/
	class InterestAmountApplicationBuilderImpl implements InterestAmountApplication.InterestAmountApplicationBuilder {
	
		protected ReturnAmount.ReturnAmountBuilder returnAmount;
		protected DeliveryAmount.DeliveryAmountBuilder deliveryAmount;
	
		public InterestAmountApplicationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("returnAmount")
		public ReturnAmount.ReturnAmountBuilder getReturnAmount() {
			return returnAmount;
		}
		
		@Override
		public ReturnAmount.ReturnAmountBuilder getOrCreateReturnAmount() {
			ReturnAmount.ReturnAmountBuilder result;
			if (returnAmount!=null) {
				result = returnAmount;
			}
			else {
				result = returnAmount = ReturnAmount.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("deliveryAmount")
		public DeliveryAmount.DeliveryAmountBuilder getDeliveryAmount() {
			return deliveryAmount;
		}
		
		@Override
		public DeliveryAmount.DeliveryAmountBuilder getOrCreateDeliveryAmount() {
			DeliveryAmount.DeliveryAmountBuilder result;
			if (deliveryAmount!=null) {
				result = deliveryAmount;
			}
			else {
				result = deliveryAmount = DeliveryAmount.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("returnAmount")
		public InterestAmountApplication.InterestAmountApplicationBuilder setReturnAmount(ReturnAmount returnAmount) {
			this.returnAmount = returnAmount==null?null:returnAmount.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("deliveryAmount")
		public InterestAmountApplication.InterestAmountApplicationBuilder setDeliveryAmount(DeliveryAmount deliveryAmount) {
			this.deliveryAmount = deliveryAmount==null?null:deliveryAmount.toBuilder();
			return this;
		}
		
		@Override
		public InterestAmountApplication build() {
			return new InterestAmountApplication.InterestAmountApplicationImpl(this);
		}
		
		@Override
		public InterestAmountApplication.InterestAmountApplicationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public InterestAmountApplication.InterestAmountApplicationBuilder prune() {
			if (returnAmount!=null && !returnAmount.prune().hasData()) returnAmount = null;
			if (deliveryAmount!=null && !deliveryAmount.prune().hasData()) deliveryAmount = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getReturnAmount()!=null && getReturnAmount().hasData()) return true;
			if (getDeliveryAmount()!=null && getDeliveryAmount().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public InterestAmountApplication.InterestAmountApplicationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			InterestAmountApplication.InterestAmountApplicationBuilder o = (InterestAmountApplication.InterestAmountApplicationBuilder) other;
			
			merger.mergeRosetta(getReturnAmount(), o.getReturnAmount(), this::setReturnAmount);
			merger.mergeRosetta(getDeliveryAmount(), o.getDeliveryAmount(), this::setDeliveryAmount);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			InterestAmountApplication _that = getType().cast(o);
		
			if (!Objects.equals(returnAmount, _that.getReturnAmount())) return false;
			if (!Objects.equals(deliveryAmount, _that.getDeliveryAmount())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (returnAmount != null ? returnAmount.hashCode() : 0);
			_result = 31 * _result + (deliveryAmount != null ? deliveryAmount.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "InterestAmountApplicationBuilder {" +
				"returnAmount=" + this.returnAmount + ", " +
				"deliveryAmount=" + this.deliveryAmount +
			'}';
		}
	}
}
