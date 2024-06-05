package cdm.base.staticdata.asset.common;

import cdm.base.datetime.AdjustableDate;
import cdm.base.datetime.Offset;
import cdm.base.staticdata.asset.common.DeliveryDateParameters;
import cdm.base.staticdata.asset.common.DeliveryDateParameters.DeliveryDateParametersBuilder;
import cdm.base.staticdata.asset.common.DeliveryDateParameters.DeliveryDateParametersBuilderImpl;
import cdm.base.staticdata.asset.common.DeliveryDateParameters.DeliveryDateParametersImpl;
import cdm.base.staticdata.asset.common.meta.DeliveryDateParametersMeta;
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
 * Specifies a specific date or the parameters for identifying the relevant contract date when the commodity reference price is a futures contract.
 * @version ${project.version}
 */
@RosettaDataType(value="DeliveryDateParameters", builder=DeliveryDateParameters.DeliveryDateParametersBuilderImpl.class, version="${project.version}")
public interface DeliveryDateParameters extends RosettaModelObject {

	DeliveryDateParametersMeta metaData = new DeliveryDateParametersMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Provides a container for the parametric representation that specifies which nearby contract date would be used as a refrence for a price.
	 */
	Offset getDeliveryNearby();
	/**
	 * Specifies the specific contract date for the contract that should be referenced for a price.
	 */
	AdjustableDate getDeliveryDate();
	/**
	 * Specifies, for a Commodity Transaction that references a listed future, the day on which the specified future will roll to the next nearby month prior to the expiration of the referenced future. If the future will not roll at all - i.e. the price will be taken from the expiring contract, 0 days should be specified here. If the future will roll to the next nearby on the last trading day - i.e. the price will be taken from the next nearby on the last trading day, then 1 business day should be specified and so on.
	 */
	Offset getDeliveryDateRollConvention();
	/**
	 * Specifies, for a Commodity Transaction that references a listed future, the day on which the specified future will expire ahead of the actual expiration of the referenced future. For example: Z21 Contract expires on 19Nov21, with an adjust of 2D the &#39;expire&#39; will be 16Nov21. DeliveryDateRollConvention takes precedence. Example: Pricing on the Z21 Contract with NearbyContractDay and a deliveryDateRoll of 10D, Sampling of the F22 Contract will occur on 8Nov21 through the last Date of the Z21 Contract. With an ExpConvention of 5D, the last sampling date on the F22 contract will be 12Nov21.
	 */
	Offset getDeliveryDateExpirationConvention();

	/*********************** Build Methods  ***********************/
	DeliveryDateParameters build();
	
	DeliveryDateParameters.DeliveryDateParametersBuilder toBuilder();
	
	static DeliveryDateParameters.DeliveryDateParametersBuilder builder() {
		return new DeliveryDateParameters.DeliveryDateParametersBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends DeliveryDateParameters> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends DeliveryDateParameters> getType() {
		return DeliveryDateParameters.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("deliveryNearby"), processor, Offset.class, getDeliveryNearby());
		processRosetta(path.newSubPath("deliveryDate"), processor, AdjustableDate.class, getDeliveryDate());
		processRosetta(path.newSubPath("deliveryDateRollConvention"), processor, Offset.class, getDeliveryDateRollConvention());
		processRosetta(path.newSubPath("deliveryDateExpirationConvention"), processor, Offset.class, getDeliveryDateExpirationConvention());
	}
	

	/*********************** Builder Interface  ***********************/
	interface DeliveryDateParametersBuilder extends DeliveryDateParameters, RosettaModelObjectBuilder {
		Offset.OffsetBuilder getOrCreateDeliveryNearby();
		Offset.OffsetBuilder getDeliveryNearby();
		AdjustableDate.AdjustableDateBuilder getOrCreateDeliveryDate();
		AdjustableDate.AdjustableDateBuilder getDeliveryDate();
		Offset.OffsetBuilder getOrCreateDeliveryDateRollConvention();
		Offset.OffsetBuilder getDeliveryDateRollConvention();
		Offset.OffsetBuilder getOrCreateDeliveryDateExpirationConvention();
		Offset.OffsetBuilder getDeliveryDateExpirationConvention();
		DeliveryDateParameters.DeliveryDateParametersBuilder setDeliveryNearby(Offset deliveryNearby);
		DeliveryDateParameters.DeliveryDateParametersBuilder setDeliveryDate(AdjustableDate deliveryDate);
		DeliveryDateParameters.DeliveryDateParametersBuilder setDeliveryDateRollConvention(Offset deliveryDateRollConvention);
		DeliveryDateParameters.DeliveryDateParametersBuilder setDeliveryDateExpirationConvention(Offset deliveryDateExpirationConvention);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("deliveryNearby"), processor, Offset.OffsetBuilder.class, getDeliveryNearby());
			processRosetta(path.newSubPath("deliveryDate"), processor, AdjustableDate.AdjustableDateBuilder.class, getDeliveryDate());
			processRosetta(path.newSubPath("deliveryDateRollConvention"), processor, Offset.OffsetBuilder.class, getDeliveryDateRollConvention());
			processRosetta(path.newSubPath("deliveryDateExpirationConvention"), processor, Offset.OffsetBuilder.class, getDeliveryDateExpirationConvention());
		}
		

		DeliveryDateParameters.DeliveryDateParametersBuilder prune();
	}

	/*********************** Immutable Implementation of DeliveryDateParameters  ***********************/
	class DeliveryDateParametersImpl implements DeliveryDateParameters {
		private final Offset deliveryNearby;
		private final AdjustableDate deliveryDate;
		private final Offset deliveryDateRollConvention;
		private final Offset deliveryDateExpirationConvention;
		
		protected DeliveryDateParametersImpl(DeliveryDateParameters.DeliveryDateParametersBuilder builder) {
			this.deliveryNearby = ofNullable(builder.getDeliveryNearby()).map(f->f.build()).orElse(null);
			this.deliveryDate = ofNullable(builder.getDeliveryDate()).map(f->f.build()).orElse(null);
			this.deliveryDateRollConvention = ofNullable(builder.getDeliveryDateRollConvention()).map(f->f.build()).orElse(null);
			this.deliveryDateExpirationConvention = ofNullable(builder.getDeliveryDateExpirationConvention()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("deliveryNearby")
		public Offset getDeliveryNearby() {
			return deliveryNearby;
		}
		
		@Override
		@RosettaAttribute("deliveryDate")
		public AdjustableDate getDeliveryDate() {
			return deliveryDate;
		}
		
		@Override
		@RosettaAttribute("deliveryDateRollConvention")
		public Offset getDeliveryDateRollConvention() {
			return deliveryDateRollConvention;
		}
		
		@Override
		@RosettaAttribute("deliveryDateExpirationConvention")
		public Offset getDeliveryDateExpirationConvention() {
			return deliveryDateExpirationConvention;
		}
		
		@Override
		public DeliveryDateParameters build() {
			return this;
		}
		
		@Override
		public DeliveryDateParameters.DeliveryDateParametersBuilder toBuilder() {
			DeliveryDateParameters.DeliveryDateParametersBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(DeliveryDateParameters.DeliveryDateParametersBuilder builder) {
			ofNullable(getDeliveryNearby()).ifPresent(builder::setDeliveryNearby);
			ofNullable(getDeliveryDate()).ifPresent(builder::setDeliveryDate);
			ofNullable(getDeliveryDateRollConvention()).ifPresent(builder::setDeliveryDateRollConvention);
			ofNullable(getDeliveryDateExpirationConvention()).ifPresent(builder::setDeliveryDateExpirationConvention);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DeliveryDateParameters _that = getType().cast(o);
		
			if (!Objects.equals(deliveryNearby, _that.getDeliveryNearby())) return false;
			if (!Objects.equals(deliveryDate, _that.getDeliveryDate())) return false;
			if (!Objects.equals(deliveryDateRollConvention, _that.getDeliveryDateRollConvention())) return false;
			if (!Objects.equals(deliveryDateExpirationConvention, _that.getDeliveryDateExpirationConvention())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (deliveryNearby != null ? deliveryNearby.hashCode() : 0);
			_result = 31 * _result + (deliveryDate != null ? deliveryDate.hashCode() : 0);
			_result = 31 * _result + (deliveryDateRollConvention != null ? deliveryDateRollConvention.hashCode() : 0);
			_result = 31 * _result + (deliveryDateExpirationConvention != null ? deliveryDateExpirationConvention.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DeliveryDateParameters {" +
				"deliveryNearby=" + this.deliveryNearby + ", " +
				"deliveryDate=" + this.deliveryDate + ", " +
				"deliveryDateRollConvention=" + this.deliveryDateRollConvention + ", " +
				"deliveryDateExpirationConvention=" + this.deliveryDateExpirationConvention +
			'}';
		}
	}

	/*********************** Builder Implementation of DeliveryDateParameters  ***********************/
	class DeliveryDateParametersBuilderImpl implements DeliveryDateParameters.DeliveryDateParametersBuilder {
	
		protected Offset.OffsetBuilder deliveryNearby;
		protected AdjustableDate.AdjustableDateBuilder deliveryDate;
		protected Offset.OffsetBuilder deliveryDateRollConvention;
		protected Offset.OffsetBuilder deliveryDateExpirationConvention;
	
		public DeliveryDateParametersBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("deliveryNearby")
		public Offset.OffsetBuilder getDeliveryNearby() {
			return deliveryNearby;
		}
		
		@Override
		public Offset.OffsetBuilder getOrCreateDeliveryNearby() {
			Offset.OffsetBuilder result;
			if (deliveryNearby!=null) {
				result = deliveryNearby;
			}
			else {
				result = deliveryNearby = Offset.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("deliveryDate")
		public AdjustableDate.AdjustableDateBuilder getDeliveryDate() {
			return deliveryDate;
		}
		
		@Override
		public AdjustableDate.AdjustableDateBuilder getOrCreateDeliveryDate() {
			AdjustableDate.AdjustableDateBuilder result;
			if (deliveryDate!=null) {
				result = deliveryDate;
			}
			else {
				result = deliveryDate = AdjustableDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("deliveryDateRollConvention")
		public Offset.OffsetBuilder getDeliveryDateRollConvention() {
			return deliveryDateRollConvention;
		}
		
		@Override
		public Offset.OffsetBuilder getOrCreateDeliveryDateRollConvention() {
			Offset.OffsetBuilder result;
			if (deliveryDateRollConvention!=null) {
				result = deliveryDateRollConvention;
			}
			else {
				result = deliveryDateRollConvention = Offset.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("deliveryDateExpirationConvention")
		public Offset.OffsetBuilder getDeliveryDateExpirationConvention() {
			return deliveryDateExpirationConvention;
		}
		
		@Override
		public Offset.OffsetBuilder getOrCreateDeliveryDateExpirationConvention() {
			Offset.OffsetBuilder result;
			if (deliveryDateExpirationConvention!=null) {
				result = deliveryDateExpirationConvention;
			}
			else {
				result = deliveryDateExpirationConvention = Offset.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("deliveryNearby")
		public DeliveryDateParameters.DeliveryDateParametersBuilder setDeliveryNearby(Offset deliveryNearby) {
			this.deliveryNearby = deliveryNearby==null?null:deliveryNearby.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("deliveryDate")
		public DeliveryDateParameters.DeliveryDateParametersBuilder setDeliveryDate(AdjustableDate deliveryDate) {
			this.deliveryDate = deliveryDate==null?null:deliveryDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("deliveryDateRollConvention")
		public DeliveryDateParameters.DeliveryDateParametersBuilder setDeliveryDateRollConvention(Offset deliveryDateRollConvention) {
			this.deliveryDateRollConvention = deliveryDateRollConvention==null?null:deliveryDateRollConvention.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("deliveryDateExpirationConvention")
		public DeliveryDateParameters.DeliveryDateParametersBuilder setDeliveryDateExpirationConvention(Offset deliveryDateExpirationConvention) {
			this.deliveryDateExpirationConvention = deliveryDateExpirationConvention==null?null:deliveryDateExpirationConvention.toBuilder();
			return this;
		}
		
		@Override
		public DeliveryDateParameters build() {
			return new DeliveryDateParameters.DeliveryDateParametersImpl(this);
		}
		
		@Override
		public DeliveryDateParameters.DeliveryDateParametersBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DeliveryDateParameters.DeliveryDateParametersBuilder prune() {
			if (deliveryNearby!=null && !deliveryNearby.prune().hasData()) deliveryNearby = null;
			if (deliveryDate!=null && !deliveryDate.prune().hasData()) deliveryDate = null;
			if (deliveryDateRollConvention!=null && !deliveryDateRollConvention.prune().hasData()) deliveryDateRollConvention = null;
			if (deliveryDateExpirationConvention!=null && !deliveryDateExpirationConvention.prune().hasData()) deliveryDateExpirationConvention = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getDeliveryNearby()!=null && getDeliveryNearby().hasData()) return true;
			if (getDeliveryDate()!=null && getDeliveryDate().hasData()) return true;
			if (getDeliveryDateRollConvention()!=null && getDeliveryDateRollConvention().hasData()) return true;
			if (getDeliveryDateExpirationConvention()!=null && getDeliveryDateExpirationConvention().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DeliveryDateParameters.DeliveryDateParametersBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			DeliveryDateParameters.DeliveryDateParametersBuilder o = (DeliveryDateParameters.DeliveryDateParametersBuilder) other;
			
			merger.mergeRosetta(getDeliveryNearby(), o.getDeliveryNearby(), this::setDeliveryNearby);
			merger.mergeRosetta(getDeliveryDate(), o.getDeliveryDate(), this::setDeliveryDate);
			merger.mergeRosetta(getDeliveryDateRollConvention(), o.getDeliveryDateRollConvention(), this::setDeliveryDateRollConvention);
			merger.mergeRosetta(getDeliveryDateExpirationConvention(), o.getDeliveryDateExpirationConvention(), this::setDeliveryDateExpirationConvention);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DeliveryDateParameters _that = getType().cast(o);
		
			if (!Objects.equals(deliveryNearby, _that.getDeliveryNearby())) return false;
			if (!Objects.equals(deliveryDate, _that.getDeliveryDate())) return false;
			if (!Objects.equals(deliveryDateRollConvention, _that.getDeliveryDateRollConvention())) return false;
			if (!Objects.equals(deliveryDateExpirationConvention, _that.getDeliveryDateExpirationConvention())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (deliveryNearby != null ? deliveryNearby.hashCode() : 0);
			_result = 31 * _result + (deliveryDate != null ? deliveryDate.hashCode() : 0);
			_result = 31 * _result + (deliveryDateRollConvention != null ? deliveryDateRollConvention.hashCode() : 0);
			_result = 31 * _result + (deliveryDateExpirationConvention != null ? deliveryDateExpirationConvention.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DeliveryDateParametersBuilder {" +
				"deliveryNearby=" + this.deliveryNearby + ", " +
				"deliveryDate=" + this.deliveryDate + ", " +
				"deliveryDateRollConvention=" + this.deliveryDateRollConvention + ", " +
				"deliveryDateExpirationConvention=" + this.deliveryDateExpirationConvention +
			'}';
		}
	}
}
