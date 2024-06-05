package cdm.product.template;

import cdm.observable.event.TriggerEvent;
import cdm.product.template.Knock;
import cdm.product.template.Knock.KnockBuilder;
import cdm.product.template.Knock.KnockBuilderImpl;
import cdm.product.template.Knock.KnockImpl;
import cdm.product.template.meta.KnockMeta;
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
 * Knock In means option to exercise comes into existence. Knock Out means option to exercise goes out of existence.
 * @version ${project.version}
 */
@RosettaDataType(value="Knock", builder=Knock.KnockBuilderImpl.class, version="${project.version}")
public interface Knock extends RosettaModelObject {

	KnockMeta metaData = new KnockMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The knock in.
	 */
	TriggerEvent getKnockIn();
	/**
	 * The knock out.
	 */
	TriggerEvent getKnockOut();

	/*********************** Build Methods  ***********************/
	Knock build();
	
	Knock.KnockBuilder toBuilder();
	
	static Knock.KnockBuilder builder() {
		return new Knock.KnockBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Knock> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Knock> getType() {
		return Knock.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("knockIn"), processor, TriggerEvent.class, getKnockIn());
		processRosetta(path.newSubPath("knockOut"), processor, TriggerEvent.class, getKnockOut());
	}
	

	/*********************** Builder Interface  ***********************/
	interface KnockBuilder extends Knock, RosettaModelObjectBuilder {
		TriggerEvent.TriggerEventBuilder getOrCreateKnockIn();
		TriggerEvent.TriggerEventBuilder getKnockIn();
		TriggerEvent.TriggerEventBuilder getOrCreateKnockOut();
		TriggerEvent.TriggerEventBuilder getKnockOut();
		Knock.KnockBuilder setKnockIn(TriggerEvent knockIn);
		Knock.KnockBuilder setKnockOut(TriggerEvent knockOut);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("knockIn"), processor, TriggerEvent.TriggerEventBuilder.class, getKnockIn());
			processRosetta(path.newSubPath("knockOut"), processor, TriggerEvent.TriggerEventBuilder.class, getKnockOut());
		}
		

		Knock.KnockBuilder prune();
	}

	/*********************** Immutable Implementation of Knock  ***********************/
	class KnockImpl implements Knock {
		private final TriggerEvent knockIn;
		private final TriggerEvent knockOut;
		
		protected KnockImpl(Knock.KnockBuilder builder) {
			this.knockIn = ofNullable(builder.getKnockIn()).map(f->f.build()).orElse(null);
			this.knockOut = ofNullable(builder.getKnockOut()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("knockIn")
		public TriggerEvent getKnockIn() {
			return knockIn;
		}
		
		@Override
		@RosettaAttribute("knockOut")
		public TriggerEvent getKnockOut() {
			return knockOut;
		}
		
		@Override
		public Knock build() {
			return this;
		}
		
		@Override
		public Knock.KnockBuilder toBuilder() {
			Knock.KnockBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Knock.KnockBuilder builder) {
			ofNullable(getKnockIn()).ifPresent(builder::setKnockIn);
			ofNullable(getKnockOut()).ifPresent(builder::setKnockOut);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Knock _that = getType().cast(o);
		
			if (!Objects.equals(knockIn, _that.getKnockIn())) return false;
			if (!Objects.equals(knockOut, _that.getKnockOut())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (knockIn != null ? knockIn.hashCode() : 0);
			_result = 31 * _result + (knockOut != null ? knockOut.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Knock {" +
				"knockIn=" + this.knockIn + ", " +
				"knockOut=" + this.knockOut +
			'}';
		}
	}

	/*********************** Builder Implementation of Knock  ***********************/
	class KnockBuilderImpl implements Knock.KnockBuilder {
	
		protected TriggerEvent.TriggerEventBuilder knockIn;
		protected TriggerEvent.TriggerEventBuilder knockOut;
	
		public KnockBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("knockIn")
		public TriggerEvent.TriggerEventBuilder getKnockIn() {
			return knockIn;
		}
		
		@Override
		public TriggerEvent.TriggerEventBuilder getOrCreateKnockIn() {
			TriggerEvent.TriggerEventBuilder result;
			if (knockIn!=null) {
				result = knockIn;
			}
			else {
				result = knockIn = TriggerEvent.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("knockOut")
		public TriggerEvent.TriggerEventBuilder getKnockOut() {
			return knockOut;
		}
		
		@Override
		public TriggerEvent.TriggerEventBuilder getOrCreateKnockOut() {
			TriggerEvent.TriggerEventBuilder result;
			if (knockOut!=null) {
				result = knockOut;
			}
			else {
				result = knockOut = TriggerEvent.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("knockIn")
		public Knock.KnockBuilder setKnockIn(TriggerEvent knockIn) {
			this.knockIn = knockIn==null?null:knockIn.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("knockOut")
		public Knock.KnockBuilder setKnockOut(TriggerEvent knockOut) {
			this.knockOut = knockOut==null?null:knockOut.toBuilder();
			return this;
		}
		
		@Override
		public Knock build() {
			return new Knock.KnockImpl(this);
		}
		
		@Override
		public Knock.KnockBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Knock.KnockBuilder prune() {
			if (knockIn!=null && !knockIn.prune().hasData()) knockIn = null;
			if (knockOut!=null && !knockOut.prune().hasData()) knockOut = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getKnockIn()!=null && getKnockIn().hasData()) return true;
			if (getKnockOut()!=null && getKnockOut().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Knock.KnockBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Knock.KnockBuilder o = (Knock.KnockBuilder) other;
			
			merger.mergeRosetta(getKnockIn(), o.getKnockIn(), this::setKnockIn);
			merger.mergeRosetta(getKnockOut(), o.getKnockOut(), this::setKnockOut);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Knock _that = getType().cast(o);
		
			if (!Objects.equals(knockIn, _that.getKnockIn())) return false;
			if (!Objects.equals(knockOut, _that.getKnockOut())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (knockIn != null ? knockIn.hashCode() : 0);
			_result = 31 * _result + (knockOut != null ? knockOut.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "KnockBuilder {" +
				"knockIn=" + this.knockIn + ", " +
				"knockOut=" + this.knockOut +
			'}';
		}
	}
}
